package com.lds.swapi.service;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lds.swapi.model.StarWarsCharacter;
import com.lds.swapi.repository.StarWarsCharacterRepository;

/**
 * Service class for managing operations on Star Wars characters.
 * This class handles all business logic associated with the entity,
 * including CRUD operations and complex queries for retrieving detailed information.
 */
@Service
public class StarWarsCharacterService {

    private final StarWarsCharacterRepository characterRepository;

    // Constructor injection for character repository
    @Autowired
    public StarWarsCharacterService(StarWarsCharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    /**
     * Retrieves a paginated list of Star Wars characters.
     * @param limit the number of characters to retrieve.
     * @param offset the offset to start the retrieval from.
     * @return a list of characters.
     */
    public List<StarWarsCharacter> findAllCharacters(int limit, int offset) {
        try {
            return characterRepository.findAllCharacters(limit, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve character: " + e.getMessage(), e);
        }
    }

    /**
     * Finds a Star Wars character by their unique identifier.
     * @param id the ID of the character to find.
     * @return the found character.
     * @throws ResponseStatusException if no character is found.
     */
    public StarWarsCharacter findCharacterById(Integer id) {
        return characterRepository.findCharacterById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found with id: " + id));
    }

    /**
     * Adds a new Star Wars character to the database.
     * @param character the character to add.
     */
    public void addCharacter(StarWarsCharacter character) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String starshipsJson = objectMapper.writeValueAsString(character.getStarships());
            characterRepository.addCharacter(character.getName(), character.getHomePlanet(), starshipsJson);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding character: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing Star Wars character.
     * @param id the ID of the character to update.
     * @param character the updated character details.
     */
    public void updateCharacter(Integer id, StarWarsCharacter character) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String starshipsJson = objectMapper.writeValueAsString(character.getStarships());
            characterRepository.updateCharacter(character.getName(), character.getHomePlanet(), starshipsJson, id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating character: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a Star Wars character from the database.
     * @param id the ID of the character to delete.
     */
    public void deleteCharacter(Integer id) {
        try {
            characterRepository.deleteCharacter(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting character: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves detailed information about a Star Wars character, including their associated starships and home planet.
     * @param id the ID of the character for whom to retrieve details.
     * @return a list of maps, each containing character details.
     */
    public List<Map<String, Object>> getCharacterDetailsById(Integer id) {
        try {
            List<Object[]> results = characterRepository.findCharacterDetailsById(id);
            if (results.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No character details found with the provided ID");
            }
            return results.stream().map(result -> {
                Map<String, Object> details = new LinkedHashMap<>();
                details.put("characterName", result[0]);
                details.put("shipNames", result[1]);
                details.put("planetName", result[2]);
                return details;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while retrieving character details: " + e.getMessage(), e);
        }
    }

}