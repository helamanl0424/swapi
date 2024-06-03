package com.lds.swapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.lds.swapi.model.GalaxyPlanet;
import com.lds.swapi.service.GalaxyPlanetService;

@RestController
@RequestMapping("/api/planets")
public class GalaxyPlanetController {

    private final GalaxyPlanetService galaxyPlanetService;

    @Autowired
    public GalaxyPlanetController(GalaxyPlanetService galaxyPlanetProductService) {
        this.galaxyPlanetService = galaxyPlanetProductService;
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanetById(@PathVariable Integer id) {
        try {
            GalaxyPlanet planet = galaxyPlanetService.findPlanetById(id);
            return ResponseEntity.ok(planet);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping
    public ResponseEntity<?> addPlanet(@RequestBody GalaxyPlanet planet) {
        try {
            galaxyPlanetService.addPlanet(planet);
            return ResponseEntity.ok("Planet added successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlanet(@PathVariable Integer id, @RequestBody GalaxyPlanet planet) {
        try {
            galaxyPlanetService.updatePlanet(id, planet);
            return ResponseEntity.ok("Planet updated successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

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