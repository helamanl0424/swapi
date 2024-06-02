package com.lds.swapi.controller;

import com.lds.swapi.model.StarWarsCharacter;
import com.lds.swapi.service.StarWarsCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class StarWarsCharacterController {

    private final StarWarsCharacterService characterService;

    @Autowired
    public StarWarsCharacterController(StarWarsCharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public List<StarWarsCharacter> getAllCharacters() {
        return characterService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarWarsCharacter> getCharacterById(@PathVariable Long id) {
        return characterService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StarWarsCharacter createCharacter(@RequestBody StarWarsCharacter character) {
        return characterService.save(character);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StarWarsCharacter> updateCharacter(@PathVariable Long id, @RequestBody StarWarsCharacter updatedCharacter) {
        return characterService.update(id, updatedCharacter)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.ok().build();
    }
}