package com.lds.swapi.service;

import com.lds.swapi.model.StarshipMaster;
import com.lds.swapi.repository.StarshipMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarshipMasterService {

    private final StarshipMasterRepository starshipRepository;

    @Autowired
    public StarshipMasterService(StarshipMasterRepository starshipRepository) {
        this.starshipRepository = starshipRepository;
    }

    public List<StarshipMaster> findAll() {
        return starshipRepository.findAll();
    }

    public Optional<StarshipMaster> findById(Long id) {
        return starshipRepository.findById(id);
    }

    public StarshipMaster save(StarshipMaster starship) {
        return starshipRepository.save(starship);
    }

    public StarshipMaster update(Long id, StarshipMaster starship) {
        return starshipRepository.findById(id)
                .map(existingStarship -> {
                    existingStarship.setName(starship.getName());
                    existingStarship.setModel(starship.getModel());
                    existingStarship.setCostInCredits(starship.getCostInCredits());
                    return starshipRepository.save(existingStarship);
                }).orElseGet(() -> save(starship));
    }

    public void delete(Long id) {
        starshipRepository.deleteById(id);
    }
}