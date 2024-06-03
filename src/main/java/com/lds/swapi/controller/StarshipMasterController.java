package com.lds.swapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.lds.swapi.model.StarshipMaster;
import com.lds.swapi.service.StarshipMasterService;

/**
 * REST controller for managing Star Wars starships.
 * Provides endpoints for CRUD operations on Star Wars starships.
 */
@RestController
@RequestMapping("/api/starships")
public class StarshipMasterController {

    private final StarshipMasterService starshipService;

    // Constructor injection for starship service
    @Autowired
    public StarshipMasterController(StarshipMasterService starshipService) {
        this.starshipService = starshipService;
    }

    // Retrieve all starships with pagination support
    @GetMapping
    public ResponseEntity<?> getAllStarships(
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "0" ) int page) {
        try {
            int offset = page * size;
            List<StarshipMaster> starshipMasters = starshipService.findAllStarships(size, offset);
            return ResponseEntity.ok(starshipMasters);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Retrieve a single starship by their ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStarshipById(@PathVariable Integer id) {
        try {
            StarshipMaster starshipMaster = starshipService.findStarshipById(id);
            return ResponseEntity.ok(starshipMaster);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Add a new starship
    @PostMapping
    public ResponseEntity<?> addStarship(@RequestBody StarshipMaster starship) {
        try {
            starshipService.addStarship(starship);
            return ResponseEntity.ok("Starship added successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Update an existing starship by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStarship(@PathVariable Integer id, @RequestBody StarshipMaster starship) {
        try {
            starshipService.updateStarship(id, starship);
            return ResponseEntity.ok("Starship updated successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Delete a starship by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStarship(@PathVariable Integer id) {
        try {
            starshipService.deleteStarship(id);
            return ResponseEntity.ok("Starship deleted successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

}