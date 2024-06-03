package com.lds.swapi.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<StarWarsCharacter>> getAllCharacters(
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "0") int page) {
        int offset = page * size;
        return ResponseEntity.ok(characterService.findAllCharacters(size, offset));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarWarsCharacter> getCharacterById(@PathVariable Integer id) {
        return characterService.findCharacterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addCharacter(@RequestBody StarWarsCharacter character) throws JsonProcessingException {
        characterService.addCharacter(character);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StarWarsCharacter> updateCharacter(@PathVariable Integer id, @RequestBody StarWarsCharacter character) throws JsonProcessingException {
        characterService.updateCharacter(id, character);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Integer id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.ok().build();
    }
}