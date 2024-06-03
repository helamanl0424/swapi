package com.lds.swapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lds.swapi.model.StarshipMaster;
import com.lds.swapi.service.StarshipMasterService;

@RestController
@RequestMapping("/api/starships")
public class StarshipMasterController {

    private final StarshipMasterService starshipService;

    @Autowired
    public StarshipMasterController(StarshipMasterService starshipService) {
        this.starshipService = starshipService;
    }

    @GetMapping
    public ResponseEntity<List<StarshipMaster>> getAllStarships(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10" ) int size) {
        int offset = page * size;
        return ResponseEntity.ok(starshipService.findAllShip(size, offset));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarshipMaster> getStarshipById(@PathVariable Integer id) {
        return starshipService.findShipById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addStarship(@RequestBody StarshipMaster starship) {
        starshipService.addShip(starship);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StarshipMaster> updateStarship(@PathVariable Integer id, @RequestBody StarshipMaster starship) {
        starshipService.updateShip(id, starship);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStarship(@PathVariable Integer id) {
        starshipService.deleteShip(id);
        return ResponseEntity.ok().build();
    }

}