package com.lds.swapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lds.swapi.model.StarWarsCharacter;

/**
 * Repository interface for Star Wars characters.
 * Extends JpaRepository
 * A personal comment below:
 * Although the JpaRepository has built-in functions supporting CURD operations,
 * I decide to write custom functions to showcase my SQL expertise.
 */
@Repository
public interface StarWarsCharacterRepository extends JpaRepository<StarWarsCharacter, Long> {

    /**
     * Retrieves a paginated list of Star Wars characters from the database.
     * @param limit the maximum number of characters to return.
     * @param offset the offset from where to start returning characters.
     * @return a list of characters.
     */
    @Query(value = "SELECT * FROM star_wars_character LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<StarWarsCharacter> findAllCharacters(int limit, int offset);

    /**
     * Finds a single Star Wars character by ID.
     * @param id the ID of the character.
     * @return an Optional containing the found character or an empty Optional if no character is found.
     */
    @Query(value = "SELECT * FROM star_wars_character WHERE id = ?1", nativeQuery = true)
    Optional<StarWarsCharacter> findCharacterById(Integer id);

    /**
     * Inserts a new Star Wars character into the database.
     * @param name the name of the character.
     * @param homePlanet the ID of the home planet of the character.
     * @param starshipsJson the JSON array of starship IDs associated with the character.
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO star_wars_character (name, home_planet, starships) VALUES (?1, ?2, CAST(?3 AS jsonb))", nativeQuery = true)
    void addCharacter(String name, Integer homePlanet, String starshipsJson);

    /**
     * Updates an existing Star Wars character in the database.
     * @param name the new name of the character.
     * @param homePlanet the new home planet ID of the character.
     * @param starshipsJson the new JSON array of starship IDs for the character.
     * @param id the ID of the character to update.
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE star_wars_character SET name = ?1, home_planet = ?2, starships = CAST(?3 AS jsonb) WHERE id = ?4", nativeQuery = true)
    void updateCharacter(String name, Integer homePlanet, String starshipsJson, Integer id);

    /**
     * Deletes a Star Wars character from the database.
     * @param id the ID of the character to delete.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM star_wars_character WHERE id = ?1", nativeQuery = true)
    void deleteCharacter(Integer id);

    /**
     * Retrieves detailed information about a Star Wars character, including associated starships and home planet.
     * @param characterId the ID of the character.
     * @return a list of objects arrays, each containing character name, aggregated ship names, and home planet name.
     */
    @Query(value = """
    SELECT 
        swc.name AS characterName, 
        array_agg(ssm.name) AS shipNames,
        gp.name AS planetName
    FROM 
        star_wars_character swc
    JOIN 
        starship_master ssm ON ssm.id = ANY(ARRAY(SELECT jsonb_array_elements_text(swc.starships)::int))
    JOIN 
        galaxy_planet gp ON gp.id = swc.home_planet
    WHERE 
        swc.id = ?1
    GROUP BY 
        swc.name, gp.name
    """, nativeQuery = true)
    List<Object[]> findCharacterDetailsById(Integer characterId);
}