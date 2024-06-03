package com.lds.swapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lds.swapi.model.StarshipMaster;
import com.lds.swapi.repository.StarshipMasterRepository;

@Service
public class StarshipMasterService {

    private final StarshipMasterRepository starshipRepository;

    @Autowired
    public StarshipMasterService(StarshipMasterRepository starshipRepository) {
        this.starshipRepository = starshipRepository;
    }

    public List<StarshipMaster> findAllStarships(int limit, int offset) {
        try {
            return starshipRepository.findAllStarships(limit, offset);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve starships: " + e.getMessage(), e);
        }
    }

    public StarshipMaster findShipById(Integer id) {
        return starshipRepository.findStarshipById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Starship not found with id: " + id));
    }

    public void addShip(StarshipMaster starship) {
        try {
            starshipRepository.addStarship(starship.getName(), starship.getModel(), starship.getCostInCredits());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding starship: " + e.getMessage(), e);
        }
    }

    public void updateShip(Integer id, StarshipMaster starship) {
        try {
            starshipRepository.updateStarship(starship.getName(), starship.getModel(), starship.getCostInCredits(), id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating starship: " + e.getMessage(), e);
        }
    }

    public void deleteShip(Integer id) {
        try {
            starshipRepository.deleteStarship(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting starship: " + e.getMessage(), e);
        }
    }
}