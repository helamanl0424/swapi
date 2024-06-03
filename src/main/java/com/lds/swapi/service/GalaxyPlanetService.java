package com.lds.swapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lds.swapi.model.GalaxyPlanet;
import com.lds.swapi.repository.GalaxyPlanetRepository;

@Service
public class GalaxyPlanetService {

    private final GalaxyPlanetRepository galaxyPlanetRepository;

    @Autowired
    public GalaxyPlanetService(GalaxyPlanetRepository galaxyPlanetRepository) {
        this.galaxyPlanetRepository = galaxyPlanetRepository;
    }

    public List<GalaxyPlanet> findAllPlanets(int limit, int offset) {
        try {
            return galaxyPlanetRepository.findAllPlanets(limit, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve planets", e);
        }
    }

    public GalaxyPlanet findPlanetById(Integer id) {
        return galaxyPlanetRepository.findPlanetById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Planet not found with id: " + id));
    }

    public void addPlanet(GalaxyPlanet planet) {
        try {
            galaxyPlanetRepository.addPlanet(planet.getName(), planet.getClimate(), planet.getPopulation());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding planet", e);
        }
    }

    public void updatePlanet(Integer id, GalaxyPlanet planet) {
        try {
            galaxyPlanetRepository.updatePlanet(planet.getName(), planet.getClimate(), planet.getPopulation(), id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating planet", e);
        }
    }

    public void deletePlanet(Integer id) {
        try {
            galaxyPlanetRepository.deletePlanet(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting planet", e);
        }
    }
}