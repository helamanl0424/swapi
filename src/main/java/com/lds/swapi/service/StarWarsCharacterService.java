package com.lds.swapi.service;

import com.lds.swapi.model.StarWarsCharacter;
import com.lds.swapi.repository.StarWarsCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import orgio.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarWarsCharacterService {

    private final StarWarsCharacterRepository characterRepository;

    @Autowired
    public StarWarsCharacterService(StarWarsCharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<StarWarsCharacter> findAll() {
        return characterRepository.findAll();
    }

    public Optional<StarWarsCharacter> findById(Long id) {
        return characterRepository.findById(id);
    }

    public StarWarsCharacter save(StarWarsCharacter character) {
        return characterRepository.save(character);
    }

    public StarWarsCharacter update(Long id, StarWarsCharacter character) {
        return characterRepository.findById(id)
                .map(existingCharacter -> {
                    existingCharacter.setName(character.getName());
                    existingCharacter.setSpecies(character.getSpecies());
                    existingCharacter.setHeight(character.getHeight());
                    existingCharacter.setHomePlanet(character.getHomePlanet());
                    return characterRepository.save(existingCharacter);
                }).orElseGet(() -> save(character));
    }

    public void delete(Long id) {
        characterRepository.deleteById(id);
    }
}