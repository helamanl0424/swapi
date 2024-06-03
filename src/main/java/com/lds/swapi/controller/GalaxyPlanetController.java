package com.lds.swapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.lds.swapi.model.GalaxyPlanet;
import com.lds.swapi.service.GalaxyPlanetService;

/**
 * REST controller for managing Star Wars planets.
 * Provides endpoints for CRUD operations on Star Wars planets.
 */
@RestController
@RequestMapping("/api/planets")
public class GalaxyPlanetController {

    private final GalaxyPlanetService galaxyPlanetService;

    // Constructor injection for galaxy planet service
    @Autowired
    public GalaxyPlanetController(GalaxyPlanetService galaxyPlanetService) {
        this.galaxyPlanetService = galaxyPlanetService;
    }

    // Retrieve all planets with pagination support
    @GetMapping
    public ResponseEntity<?> getAllPlanets(
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "0") int page) {
        try {
            int offset = page * size;
            List<GalaxyPlanet> planets = galaxyPlanetService.findAllPlanets(size, offset);
            return ResponseEntity.ok(planets);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Retrieve a single planet by their ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanetById(@PathVariable Integer id) {
        try {
            GalaxyPlanet planet = galaxyPlanetService.findPlanetById(id);
            return ResponseEntity.ok(planet);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Add a new planet
    @PostMapping
    public ResponseEntity<?> addPlanet(@RequestBody GalaxyPlanet planet) {
        try {
            galaxyPlanetService.addPlanet(planet);
            return ResponseEntity.ok("Planet added successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Update an existing planet by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlanet(@PathVariable Integer id, @RequestBody GalaxyPlanet planet) {
        try {
            galaxyPlanetService.updatePlanet(id, planet);
            return ResponseEntity.ok("Planet updated successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Delete a planet by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlanet(@PathVariable Integer id) {
        try {
            galaxyPlanetService.deletePlanet(id);
            return ResponseEntity.ok("Planet deleted successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}