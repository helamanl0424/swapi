package com.lds.swapi.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "star_war_character")
public class StarWarsCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "home_planet_id")
    private GalaxyPlanet homePlanet;

    // Foreign key for many-to-many relationship handled via join table, not a direct JSONB column here
    @ManyToMany
    @JoinTable(
            name = "character_starships",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "starship_id")
    )
    private Set<StarshipMaster> starships = new HashSet<>();

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

    public GalaxyPlanet getHomePlanet() {
        return homePlanet;
    }

    public void setHomePlanet(GalaxyPlanet homePlanet) {
        this.homePlanet = homePlanet;
    }

    public Set<StarshipMaster> getStarships() {
        return starships;
    }

    public void setStarships(Set<StarshipMaster> starships) {
        this.starships = starships;
    }
}