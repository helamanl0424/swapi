package com.lds.swapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lds.swapi.model.StarshipMaster;
import com.lds.swapi.repository.StarshipMasterRepository;

@Service
public class StarshipMasterService {

    private final StarshipMasterRepository starshipRepository;

    @Autowired
    public StarshipMasterService(StarshipMasterRepository starshipRepository) {
        this.starshipRepository = starshipRepository;
    }

    public List<StarshipMaster> findAllShip(int limit, int offset) {
        return starshipRepository.findAllStarships(limit, offset);
    }

    public Optional<StarshipMaster> findShipById(Integer id) {
        return starshipRepository.findStarshipById(id);
    }

    public void addShip(StarshipMaster starship) {
        starshipRepository.addStarship(starship.getName(), starship.getModel(), starship.getCostInCredits());
    }

    public void updateShip(Integer id, StarshipMaster starship) {
        starshipRepository.updateStarship(starship.getName(), starship.getModel(), starship.getCostInCredits(), id);
    }

    public void deleteShip(Integer id) {
        starshipRepository.deleteStarship(id);
    }
}