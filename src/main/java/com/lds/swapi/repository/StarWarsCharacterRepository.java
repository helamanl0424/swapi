package com.lds.swapi.repository;

import com.lds.swapi.model.StarWarsCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarWarsCharacterRepository extends JpaRepository<StarWarsCharacter, Long> {

}