package com.lds.swapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lds.swapi.model.StarWarsCharacter;
@Repository
public interface StarWarsCharacterRepository extends JpaRepository<StarWarsCharacter, Long> {

    @Query(value = "SELECT * FROM star_wars_character LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<StarWarsCharacter> findAllCharacters(int limit, int offset);

    @Query(value = "SELECT * FROM star_wars_character WHERE id = ?1", nativeQuery = true)
    Optional<StarWarsCharacter> findCharacterById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO star_wars_character (name, home_planet, starships) VALUES (:name, :homePlanet, cast(:starshipsJson as jsonb))", nativeQuery = true)
    void addCharacter(String name, Integer homePlanet, String starshipsJson);

    @Modifying
    @Transactional
    @Query(value = "UPDATE star_wars_character SET name = :name, home_planet = :homePlanet, starships = cast(:starshipsJson as jsonb) WHERE id = :id", nativeQuery = true)
    void updateCharacter(String name, Integer homePlanet, String starshipsJson, Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM star_wars_character WHERE id = ?1", nativeQuery = true)
    void deleteCharacter(Integer id);

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