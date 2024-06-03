package com.lds.swapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lds.swapi.model.GalaxyPlanet;
import com.lds.swapi.repository.GalaxyPlanetRepository;

@Service
public class GalaxyPlanetService {

    private final GalaxyPlanetRepository galaxyPlanetRepository;

    @Autowired
    public GalaxyPlanetService(GalaxyPlanetRepository galaxyPlanetRepository) {
        this.galaxyPlanetRepository = galaxyPlanetRepository;
    }

    // Method to find all planets with pagination
    public List<GalaxyPlanet> findAllPlanets(int limit, int offset) {
        return galaxyPlanetRepository.findAllPlanets(limit, offset);
    }

    public Optional<GalaxyPlanet> findPlanetById(Integer id) {
        return galaxyPlanetRepository.findPlanetById(id);
    }

    public void addPlanet(GalaxyPlanet planet) {
        galaxyPlanetRepository.addPlanet(planet.getName(), planet.getClimate(), planet.getPopulation());
    }

    public void updatePlanet(Integer id, GalaxyPlanet planet) {
        galaxyPlanetRepository.updatePlanet(planet.getName(), planet.getClimate(), planet.getPopulation(), id);
    }

    public void deletePlanet(Integer id) {
        galaxyPlanetRepository.deletePlanet(id);
    }
}