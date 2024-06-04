package com.lds.swapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lds.swapi.model.StarshipMaster;

/**
 * Repository interface for Star Wars starships.
 * Extends JpaRepository
 * A personal comment below:
 * Although the JpaRepository has built-in functions supporting CRUD operations,
 * I decide to write custom functions to showcase my SQL expertise.
 */
@Repository
public interface StarshipMasterRepository extends JpaRepository<StarshipMaster, Long> {

    /**
     * Retrieves a paginated list of Star Wars starships from the database.
     * @param limit the maximum number of starships to return.
     * @param offset the offset from where to start returning starships.
     * @return a list of starships.
     */
    @Query(value = "SELECT * FROM starship_master LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<StarshipMaster> findAllStarships(int limit, int offset);

    /**
     * Finds a single Star Wars starship by ID.
     * @param id the ID of the starship.
     * @return an Optional containing the found starship or an empty Optional if no starship is found.
     */
    @Query(value = "SELECT * FROM starship_master WHERE id = :id", nativeQuery = true)
    Optional<StarshipMaster> findStarshipById(Integer id);

    /**
     * Inserts a new Star Wars starship into the database.
     * @param name the name of the starship.
     * @param model the model of the starship.
     * @param costInCredits the cost in credits of the starship.
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO starship_master (name, model, cost_in_credits) VALUES (?1, ?2, CAST(?3 AS money))", nativeQuery = true)
    void addStarship(String name, String model, String costInCredits);

    /**
     * Updates an existing Star Wars starship in the database.
     * @param name the new name of the starship.
     * @param model the new model of the starship.
     * @param costInCredits the new cost in credits of the starship.
     * @param id the ID of the starship to update.
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE starship_master SET name = ?1, model = ?2, cost_in_credits = CAST(?3 AS money) WHERE id = ?4", nativeQuery = true)
    void updateStarship(String name, String model, String costInCredits, Integer id);

    /**
     * Deletes a Star Wars starship from the database.
     * @param id the ID of the starship to delete.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM starship_master WHERE id = ?1", nativeQuery = true)
    void deleteStarship(Integer id);

}
