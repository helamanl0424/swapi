package com.lds.swapi.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "galaxy_planet")
public class GalaxyPlanet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String climate;
    private Integer population;

//    @OneToMany(mappedBy = "homePlanet", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<StarWarsCharacter> residents = new HashSet<>();

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

//    public Set<StarWarsCharacter> getResidents() {
//        return residents;
//    }
//
//    public void setResidents(Set<StarWarsCharacter> residents) {
//        this.residents = residents;
//    }
}