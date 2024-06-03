package com.lds.swapi.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public void addCharacter(StarWarsCharacter character) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String starshipsJson = mapper.writeValueAsString(character.getStarships());
        characterRepository.addCharacter(character.getName(), character.getHomePlanet(), character.getStarships());
    }

    public void updateCharacter(Integer id, StarWarsCharacter character) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String starshipsJson = mapper.writeValueAsString(character.getStarships());
        characterRepository.updateCharacter(character.getName(), character.getHomePlanet(), character.getStarships(), id);
    }

    public void deleteCharacter(Integer id) {
        characterRepository.deleteCharacter(id);
    }
}