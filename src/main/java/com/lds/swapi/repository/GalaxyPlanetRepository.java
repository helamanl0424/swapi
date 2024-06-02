package com.lds.swapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lds.swapi.model.GalaxyPlanet;

@Repository
public interface GalaxyPlanetRepository extends JpaRepository<GalaxyPlanet, Long> {

    @Query(value = "SELECT * FROM galaxy_planet LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<GalaxyPlanet> findAllPlanets(int limit, int offset);

    @Query(value = "SELECT * FROM galaxy_planet WHERE id = ?1", nativeQuery = true)
    Optional<GalaxyPlanet> findPlanetById(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO galaxy_planet (name, climate, population) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void addPlanet(String name, String climate, Integer population);

    @Modifying
    @Transactional
    @Query(value = "UPDATE galaxy_planet SET name = ?1, climate = ?2, population = ?3 WHERE id = ?4", nativeQuery = true)
    void updatePlanet(String name, String climate, Integer population, Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM galaxy_planet WHERE id = ?1", nativeQuery = true)
    void deletePlanet(Long id);

}