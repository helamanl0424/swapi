package com.lds.swapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lds.swapi.model.GalaxyPlanet;

/**
 * Repository interface for Star Wars planets.
 * Extends JpaRepository
 * A personal comment below:
 * Although the JpaRepository has built-in functions supporting CURD operations,
 * I decide to write custom functions to showcase my SQL expertise.
 */
@Repository
public interface GalaxyPlanetRepository extends JpaRepository<GalaxyPlanet, Long> {

    /**
     * Retrieves a paginated list of Star Wars planets from the database.
     * @param limit the maximum number of planets to return.
     * @param offset the offset from where to start returning planets.
     * @return a list of planets.
     */
    @Query(value = "SELECT * FROM galaxy_planet LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<GalaxyPlanet> findAllPlanets(int limit, int offset);

    /**
     * Finds a single Star Wars planet by ID.
     * @param id the ID of the planet.
     * @return an Optional containing the found planet or an empty Optional if no planet is found.
     */
    @Query(value = "SELECT * FROM galaxy_planet WHERE id = ?1", nativeQuery = true)
    Optional<GalaxyPlanet> findPlanetById(Integer id);

    /**
     * Inserts a new Star Wars planet into the database.
     * @param name the name of the planet.
     * @param climate the climate of the planet.
     * @param population the population of the planet.
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO galaxy_planet (name, climate, population) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void addPlanet(String name, String climate, Integer population);

    /**
     * Updates an existing Star Wars planet in the database.
     * @param name the new name of the planet.
     * @param climate the new climate of the planet.
     * @param population the new population of the planet.
     * @param id the ID of the planet to update.
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE galaxy_planet SET name = ?1, climate = ?2, population = ?3 WHERE id = ?4", nativeQuery = true)
    void updatePlanet(String name, String climate, Integer population, Integer id);

    /**
     * Deletes a Star Wars planet from the database.
     * @param id the ID of the planet to delete.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM galaxy_planet WHERE id = ?1", nativeQuery = true)
    void deletePlanet(Integer id);

}