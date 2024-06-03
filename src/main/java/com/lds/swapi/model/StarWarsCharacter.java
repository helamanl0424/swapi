package com.lds.swapi.model;

import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "home_planet")
    private Integer homePlanet;
//    @ManyToMany
//    @JoinTable(name = "starship_master")
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> starships = new ArrayList<>();

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

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

}