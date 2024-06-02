package com.lds.swapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lds.swapi.model.StarshipMaster;


@Repository
public interface StarshipMasterRepository extends JpaRepository<StarshipMaster, Long> {

}