package com.lds.swapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<?> getAllStarships(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10" ) int size) {
        try {
            int offset = page * size;
            List<StarshipMaster> starshipMasters = starshipService.findAllStarships(size, offset);
            return ResponseEntity.ok(starshipMasters);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStarshipById(@PathVariable Integer id) {
        try {
            StarshipMaster starshipMaster = starshipService.findShipById(id);
            return ResponseEntity.ok(starshipMaster);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping
    public ResponseEntity<?> addStarship(@RequestBody StarshipMaster starship) {
        try {
            starshipService.addShip(starship);
            return ResponseEntity.ok("Starship added successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStarship(@PathVariable Integer id, @RequestBody StarshipMaster starship) {
        try {
            starshipService.updateShip(id, starship);
            return ResponseEntity.ok("Starship updated successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStarship(@PathVariable Integer id) {
        try {
            starshipService.deleteShip(id);
            return ResponseEntity.ok("Starship deleted successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

}