package com.lds.swapi.repository;

import com.lds.swapi.model.StarshipMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarshipMasterRepository extends JpaRepository<StarshipMaster, Long> {

}