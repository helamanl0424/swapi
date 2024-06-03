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

@RestController
@RequestMapping("/api/people")
public class StarWarsCharacterController {

    private final StarWarsCharacterService characterService;

    @Autowired
    public StarWarsCharacterController(StarWarsCharacterService characterService) {
        this.characterService = characterService;
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCharacterById(@PathVariable Integer id) {
        try {
            StarWarsCharacter character = characterService.findCharacterById(id);
            return ResponseEntity.ok(character);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping
    public ResponseEntity<?> addCharacter(@RequestBody StarWarsCharacter character) {
        try {
            characterService.addCharacter(character);
            return ResponseEntity.ok("Character added successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCharacter(@PathVariable Integer id, @RequestBody StarWarsCharacter character) {
        try {
            characterService.updateCharacter(id, character);
            return ResponseEntity.ok("Character updated successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCharacter(@PathVariable Integer id) {
        try {
            characterService.deleteCharacter(id);
            return ResponseEntity.ok("Character deleted successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

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