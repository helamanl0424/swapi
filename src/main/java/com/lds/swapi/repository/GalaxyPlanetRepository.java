package com.lds.swapi.repository;

import com.lds.swapi.model.GalaxyPlanet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface GalaxyPlanetRepository extends JpaRepository<GalaxyPlanet, Long> {

    @Query(value = "SELECT g FROM GalaxyPlanet g ORDER BY g.id ASC")
    List<GalaxyPlanet> findLimitedList(Pageable pageable);

}