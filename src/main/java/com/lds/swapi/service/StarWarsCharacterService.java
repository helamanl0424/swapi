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

@Service
public class StarWarsCharacterService {

    private final StarWarsCharacterRepository characterRepository;

    @Autowired
    public StarWarsCharacterService(StarWarsCharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<StarWarsCharacter> findAllCharacters(int limit, int offset) {
        try {
            return characterRepository.findAllCharacters(limit, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve character: " + e.getMessage(), e);
        }
    }

    public StarWarsCharacter findCharacterById(Integer id) {
        return characterRepository.findCharacterById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found with id" + id));
    }

    public void addCharacter(StarWarsCharacter character) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String starshipsJson = objectMapper.writeValueAsString(character.getStarships());
            characterRepository.addCharacter(character.getName(), character.getHomePlanet(), starshipsJson);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding character: " + e.getMessage(), e);
        }
    }

    public void updateCharacter(Integer id, StarWarsCharacter character) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String starshipsJson = objectMapper.writeValueAsString(character.getStarships());
            characterRepository.updateCharacter(character.getName(), character.getHomePlanet(), starshipsJson, id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating character: " + e.getMessage(), e);
        }
    }

    public void deleteCharacter(Integer id) {
        try {
            characterRepository.deleteCharacter(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting character: " + e.getMessage(), e);
        }
    }

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