package com.lds.swapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lds.swapi.model.StarshipMaster;
import com.lds.swapi.repository.StarshipMasterRepository;

/**
 * Service class for managing operations on Star Wars starships.
 * This class handles all business logic associated with the entity,
 * including CRUD operations.
 */
@Service
public class StarshipMasterService {

    private final StarshipMasterRepository starshipRepository;

    // Constructor injection for starship repository
    @Autowired
    public StarshipMasterService(StarshipMasterRepository starshipRepository) {
        this.starshipRepository = starshipRepository;
    }

    /**
     * Retrieves a paginated list of Star Wars starships.
     * @param limit the number of starships to retrieve.
     * @param offset the offset to start the retrieval from.
     * @return a list of starships.
     */
    public List<StarshipMaster> findAllStarships(int limit, int offset) {
        try {
            return starshipRepository.findAllStarships(limit, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve starships: " + e.getMessage(), e);
        }
    }

    /**
     * Finds a Star Wars starship by their unique identifier.
     * @param id the ID of the starship to find.
     * @return the found starship.
     * @throws ResponseStatusException if no starship is found.
     */
    public StarshipMaster findStarshipById(Integer id) {
        return starshipRepository.findStarshipById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Starship not found with id: " + id));
    }

    /**
     * Adds a new Star Wars starship to the database.
     * @param starship the starship to add.
     */
    public void addStarship(StarshipMaster starship) {
        try {
            starshipRepository.addStarship(starship.getName(), starship.getModel(), starship.getCostInCredits());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding starship: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing Star Wars starship.
     * @param id the ID of the starship to update.
     * @param starship the updated starship details.
     */
    public void updateStarship(Integer id, StarshipMaster starship) {
        try {
            starshipRepository.updateStarship(starship.getName(), starship.getModel(), starship.getCostInCredits(), id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating starship: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a Star Wars starship from the database.
     * @param id the ID of the starship to delete.
     */
    public void deleteStarship(Integer id) {
        try {
            starshipRepository.deleteStarship(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting starship: " + e.getMessage(), e);
        }
    }
}