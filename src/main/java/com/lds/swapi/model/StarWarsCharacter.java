package com.lds.swapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "star_wars_character")
public class StarWarsCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
//    @ManyToOne
//    @JoinColumn(name = "home_planet", referencedColumnName = "id")
    @JsonProperty("home_planet")
    private Integer homePlanet;
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