package com.lds.swapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

/**
 * Represents a character from the Star Wars universe.
 */
@Entity
@Table(name = "star_wars_character")
public class StarWarsCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    // Foreign key to the 'galaxy_planet' table, storing the ID of the home planet
    // This field is mapped to 'home_planet' column in the database
//    @ManyToOne
//    @JoinColumn(name = "home_planet", referencedColumnName = "id")
    @JsonProperty("home_planet")
    private Integer homePlanet;

    // Stores a list of starship IDs as JSONB. The starships are not mapped as entities here
    // but instead just represented as a list of their IDs for simplicity.
//    @ManyToMany
//    @JoinColumn(name = "starships", referencedColumnName = "id")
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Integer> starships = new ArrayList<>();

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

    public List<Integer> getStarships() {
        return starships;
    }

    public void setStarships(List<Integer> starships) {
        this.starships = starships;
    }

}