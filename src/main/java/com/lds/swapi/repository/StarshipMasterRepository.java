package com.lds.swapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lds.swapi.model.StarshipMaster;

import javax.money.MonetaryAmount;

@Repository
public interface StarshipMasterRepository extends JpaRepository<StarshipMaster, Long> {

    @Query(value = "SELECT * FROM starship_master LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<StarshipMaster> findAllStarships(int limit, int offset);

    @Query(value = "SELECT * FROM starship_master WHERE id = ?1", nativeQuery = true)
    Optional<StarshipMaster> findStarshipById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO starship_master (name, model, cost_in_credits) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void addStarship(String name, String model, Double costInCredits);

    @Modifying
    @Transactional
    @Query(value = "UPDATE starship_master SET name = ?1, model = ?2, cost_in_credits = ?3 WHERE id = ?4", nativeQuery = true)
    void updateStarship(String name, String model, Double costInCredits, Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM starship_master WHERE id = ?1", nativeQuery = true)
    void deleteStarship(Integer id);

}