package com.lds.swapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lds.swapi.model.StarWarsCharacter;
import com.lds.swapi.repository.StarWarsCharacterRepository;

@Service
public class StarWarsCharacterService {

    private final StarWarsCharacterRepository characterRepository;

    @Autowired
    public StarWarsCharacterService(StarWarsCharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<StarWarsCharacter> findAllCharacters(int limit, int offset) {
        return characterRepository.findAllCharacters(limit, offset);
    }

    public Optional<StarWarsCharacter> findCharacterById(Integer id) {
        return characterRepository.findCharacterById(id);
    }

    public void addCharacter(StarWarsCharacter character) {
        characterRepository.addCharacter(character.getName(), character.getHomePlanet(), character.getStarships());
    }

    public void updateCharacter(Integer id, StarWarsCharacter character) {
        characterRepository.updateCharacter(character.getName(), character.getHomePlanet(), character.getStarships(), id);
    }

    public void deleteCharacter(Integer id) {
        characterRepository.deleteCharacter(id);
    }
}