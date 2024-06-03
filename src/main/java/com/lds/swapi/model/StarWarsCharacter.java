package com.lds.swapi.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "star_wars_character")
public class StarWarsCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JoinColumn(name = "home_planet")
    private Integer homePlanet;

    // Foreign key for many-to-many relationship handled via join table, not a direct JSONB column here
    @ManyToMany
    @JoinTable(name = "character_starships")
    private Set<StarshipMaster> starships = new HashSet<>();

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHomePlanet() {
        return homePlanet;
    }

    public void setHomePlanet(Integer homePlanet) {
        this.homePlanet = homePlanet;
    }

    public Set<StarshipMaster> getStarships() {
        return starships;
    }

    public void setStarships(Set<StarshipMaster> starships) {
        this.starships = starships;
    }

}