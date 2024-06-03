package com.lds.swapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.lds.swapi.model.StarWarsCharacter;
import com.lds.swapi.service.StarWarsCharacterService;

/**
 * REST controller for managing Star Wars characters.
 * Provides endpoints for CRUD operations on Star Wars characters.
 */
@RestController
@RequestMapping("/api/people")
public class StarWarsCharacterController {

    private final StarWarsCharacterService characterService;

    // Constructor injection for character service
    @Autowired
    public StarWarsCharacterController(StarWarsCharacterService characterService) {
        this.characterService = characterService;
    }

    // Retrieve all characters with pagination support
    @GetMapping
    public ResponseEntity<?> getAllCharacters(
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "0") int page) {
        try {
            int offset = page * size;
            List<StarWarsCharacter> characters = characterService.findAllCharacters(size, offset);
            return ResponseEntity.ok(characters);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Retrieve a single character by their ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCharacterById(@PathVariable Integer id) {
        try {
            StarWarsCharacter character = characterService.findCharacterById(id);
            return ResponseEntity.ok(character);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Add a new character
    @PostMapping
    public ResponseEntity<?> addCharacter(@RequestBody StarWarsCharacter character) {
        try {
            characterService.addCharacter(character);
            return ResponseEntity.ok("Character added successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Update an existing character by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCharacter(@PathVariable Integer id, @RequestBody StarWarsCharacter character) {
        try {
            characterService.updateCharacter(id, character);
            return ResponseEntity.ok("Character updated successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Delete a character by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCharacter(@PathVariable Integer id) {
        try {
            characterService.deleteCharacter(id);
            return ResponseEntity.ok("Character deleted successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    // Retrieve detailed information about a character including their associated starships and home planet.
    @GetMapping("/details/{id}")
    public ResponseEntity<List<Map<String, Object>>> getCharacterDetails(@PathVariable Integer id) {
        try {
            List<Map<String, Object>> details = characterService.getCharacterDetailsById(id);
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}