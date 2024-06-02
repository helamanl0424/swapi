package com.lds.swapi.controller;

import com.lds.swapi.model.StarshipMaster;
import com.lds.swapi.service.StarshipMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/starships")
public class StarshipMasterController {

    private final StarshipMasterService starshipService;

    @Autowired
    public StarshipMasterController(StarshipMasterService starshipService) {
        this.starshipService = starshipService;
    }

    @GetMapping
    public List<StarshipMaster> getAllStarships() {
        return starshipService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarshipMaster> getStarshipById(@PathVariable Long id) {
        return starshipService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StarshipMaster createStarship(@RequestBody StarshipMaster starship) {
        return starshipService.save(starship);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StarshipMaster> updateStarship(@PathVariable Long id, @RequestBody StarshipMaster updatedStarship) {
        return starshipService.update(id, updatedStarship)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStarship(@PathVariable Long id) {
        starshipService.delete(id);
        return ResponseEntity.ok().build();
    }
}