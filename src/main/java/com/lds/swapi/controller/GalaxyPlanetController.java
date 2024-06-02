package com.lds.swapi.controller;

import com.lds.swapi.service.GalaxyPlanetService;
import com.lds.swapi.model.GalaxyPlanet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import java.util.List;

@RestController
@RequestMapping("/api/planets")
public class GalaxyPlanetController {

    private final GalaxyPlanetService galaxyPlanetService;

    @Autowired
    public GalaxyPlanetController(GalaxyPlanetService galaxyPlanetService) {
        this.galaxyPlanetService = galaxyPlanetService;
    }

    @GetMapping
    public List<GalaxyPlanet> getAllPlanets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return galaxyPlanetService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GalaxyPlanet> getPlanetById(@PathVariable Long id) {
        return galaxyPlanetService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public GalaxyPlanet createPlanet(@RequestBody GalaxyPlanet galaxyPlanet) {
        return galaxyPlanetService.save(galaxyPlanet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GalaxyPlanet> updatePlanet(@PathVariable Long id, @RequestBody GalaxyPlanet updatedPlanet) {
        return galaxyPlanetService.update(id, updatedPlanet)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
        galaxyPlanetService.delete(id);
        return ResponseEntity.ok().build();
    }
}