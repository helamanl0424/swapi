package com.lds.swapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.*;
import org.hibernate.annotations.CompositeType;
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;

@Entity
@Table(name = "starship_master")
public class StarshipMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String model;
//    @CompositeType(MonetaryAmountType.class)
//    @Column(columnDefinition = "money")
    @JsonProperty("cost_in_credits")
    private Double costInCredits;

    // Getters and Setters
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

}