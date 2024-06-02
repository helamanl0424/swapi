package com.lds.swapi.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "starship_master")
public class StarshipMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String model;
    private Double costInCredits; // Money type in PostgreSQL can be mapped to Double in Java.

    @ManyToMany(mappedBy = "starships")
    private Set<StarWarsCharacter> characters = new HashSet<>();

    // Constructors
    public StarshipMaster() {
    }

    // Getters and Setters
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getCostInCredits() {
        return costInCredits;
    }

    public void setCostInCredits(Double costInCredits) {
        this.costInCredits = costInCredits;
    }

    public Set<StarWarsCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<StarWarsCharacter> characters) {
        this.characters = characters;
        // Update bidirectional association
        for (StarWarsCharacter character : characters) {
            character.getStarships().add(this);
        }
    }
}