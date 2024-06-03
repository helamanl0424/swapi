package com.lds.swapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lds.swapi.model.GalaxyPlanet;
import com.lds.swapi.service.GalaxyPlanetService;

@RestController
@RequestMapping("/api/planets")
public class GalaxyPlanetController {

    private final GalaxyPlanetService galaxyPlanetService;

    @Autowired
    public GalaxyPlanetController(GalaxyPlanetService galaxyPlanetService) {
        this.galaxyPlanetService = galaxyPlanetService;
    }

    @GetMapping
    public ResponseEntity<List<GalaxyPlanet>> getAllPlanets(
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "0") int page) {
        int offset = page * size;
        return ResponseEntity.ok(galaxyPlanetService.findAllPlanets(size, offset));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GalaxyPlanet> getPlanetById(@PathVariable Integer id) {
        return galaxyPlanetService.findPlanetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addPlanet(@RequestBody GalaxyPlanet planet) {
        galaxyPlanetService.addPlanet(planet);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePlanet(@PathVariable Integer id, @RequestBody GalaxyPlanet planet) {
        galaxyPlanetService.updatePlanet(id, planet);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Integer id) {
        galaxyPlanetService.deletePlanet(id);
        return ResponseEntity.ok().build();
    }

}