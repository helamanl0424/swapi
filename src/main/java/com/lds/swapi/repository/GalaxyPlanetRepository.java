package com.lds.swapi.repository;

import com.lds.swapi.model.GalaxyPlanet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalaxyPlanetRepository extends JpaRepository<GalaxyPlanet, Long> {

}