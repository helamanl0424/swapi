package com.lds.swapi.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.lds.swapi.model.StarshipMaster;
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
    @Query(value = "INSERT INTO star_wars_character (name, home_planet, starships) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void addCharacter(String name, Integer homePlanet, Set<StarshipMaster> starships);

    @Modifying
    @Transactional
    @Query(value = "UPDATE star_wars_character SET name = ?1, home_planet = ?2, starships = ?3 WHERE id = ?4", nativeQuery = true)
    void updateCharacter(String name, Integer homePlanet, Set<StarshipMaster> starships, Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM star_wars_character WHERE id = ?1", nativeQuery = true)
    void deleteCharacter(Integer id);
}