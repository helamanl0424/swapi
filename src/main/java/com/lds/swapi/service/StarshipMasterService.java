package com.lds.swapi.service;

import com.lds.swapi.model.StarshipMaster;
import com.lds.swapi.repository.StarshipMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    public List<StarshipMaster> findAll(Pageable pageable) {
        return starshipRepository.findAll(pageable).getContent();
    }

    public Optional<StarshipMaster> findById(Long id) {
        return starshipRepository.findById(id);
    }

    public StarshipMaster save(StarshipMaster starship) {
        return starshipRepository.save(starship);
    }

    public Optional<StarshipMaster> update(Long id, StarshipMaster updatedStarship) {
        return starshipRepository.findById(id)
                .map(existingStarship -> {
                    existingStarship.setName(updatedStarship.getName());
                    existingStarship.setModel(updatedStarship.getModel());
                    existingStarship.setCostInCredits(updatedStarship.getCostInCredits());
                    return Optional.of(starshipRepository.save(existingStarship));
                }).orElse(Optional.empty());
    }

    public void delete(Long id) {
        starshipRepository.deleteById(id);
    }
}