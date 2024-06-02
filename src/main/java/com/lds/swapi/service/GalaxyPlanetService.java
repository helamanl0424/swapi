package com.lds.swapi.service;

import com.lds.swapi.model.GalaxyPlanet;
import com.lds.swapi.repository.GalaxyPlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GalaxyPlanetService {

    private final GalaxyPlanetRepository galaxyPlanetRepository;

    @Autowired
    public GalaxyPlanetService(GalaxyPlanetRepository galaxyPlanetRepository) {
        this.galaxyPlanetRepository = galaxyPlanetRepository;
    }

    public List<GalaxyPlanet> findAll() {
        return galaxyPlanetRepository.findAll();
    }

    public Optional<GalaxyPlanet> findById(Long id) {
        return galaxyPlanetRepository.findById(id);
    }

    public GalaxyPlanet save(GalaxyPlanet galaxyPlanet) {
        return galaxyPlanetRepository.save(galaxyPlanet);
    }

    public GalaxyPlanet update(Long id, GalaxyPlanet galaxyPlanet) {
        return galaxyPlanetRepository.findById(id)
                .map(existingPlanet -> {
                    existingPlanet.setName(galaxyPlanet.getName());
                    existingPlanet.setClimate(galaxyPlanet.getClimate());
                    existingPlanet.setPopulation(galaxyPlanet.getPopulation());
                    return galaxyPlanetRepository.save(existingPlanet);
                }).orElseGet(() -> save(galaxyPlanet));
    }

    public void delete(Long id) {
        galaxyPlanetRepository.deleteById(id);
    }
}