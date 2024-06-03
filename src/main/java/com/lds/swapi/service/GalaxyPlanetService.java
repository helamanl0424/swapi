package com.lds.swapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lds.swapi.model.GalaxyPlanet;
import com.lds.swapi.repository.GalaxyPlanetRepository;

/**
 * Service class for managing operations on Star Wars planets.
 * This class handles all business logic associated with the entity,
 * including CRUD operations.
 */
@Service
public class GalaxyPlanetService {

    private final GalaxyPlanetRepository galaxyPlanetRepository;

    // Constructor injection for galaxy planet repository
    @Autowired
    public GalaxyPlanetService(GalaxyPlanetRepository galaxyPlanetRepository) {
        this.galaxyPlanetRepository = galaxyPlanetRepository;
    }

    /**
     * Retrieves a paginated list of Star Wars planets.
     * @param limit the number of planets to retrieve.
     * @param offset the offset to start the retrieval from.
     * @return a list of planets.
     */
    public List<GalaxyPlanet> findAllPlanets(int limit, int offset) {
        try {
            return galaxyPlanetRepository.findAllPlanets(limit, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve planets: " + e.getMessage(), e);
        }
    }

    /**
     * Finds a Star Wars planet by their unique identifier.
     * @param id the ID of the planet to find.
     * @return the found planet.
     * @throws ResponseStatusException if no planet is found.
     */
    public GalaxyPlanet findPlanetById(Integer id) {
        return galaxyPlanetRepository.findPlanetById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Planet not found with id: " + id));
    }

    /**
     * Adds a new Star Wars planet to the database.
     * @param planet the planet to add.
     */
    public void addPlanet(GalaxyPlanet planet) {
        try {
            galaxyPlanetRepository.addPlanet(planet.getName(), planet.getClimate(), planet.getPopulation());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding planet: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing Star Wars planet.
     * @param id the ID of the planet to update.
     * @param planet the updated planet details.
     */
    public void updatePlanet(Integer id, GalaxyPlanet planet) {
        try {
            galaxyPlanetRepository.updatePlanet(planet.getName(), planet.getClimate(), planet.getPopulation(), id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating planet: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a Star Wars planet from the database.
     * @param id the ID of the planet to delete.
     */
    public void deletePlanet(Integer id) {
        try {
            galaxyPlanetRepository.deletePlanet(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting planet: " + e.getMessage(), e);
        }
    }
}