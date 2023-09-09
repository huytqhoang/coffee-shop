package com.coffeeshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalTime;
import java.util.List;

@Entity
@Setter
@Getter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String contactDetails;
    @Column(columnDefinition = "TIME")
    private LocalTime openingTime;
    @Column(columnDefinition = "TIME")
    private LocalTime closingTime;

    @OneToMany(mappedBy = "shop")
    @JsonManagedReference
    private List<CoffeeMenu> coffeeMenuList;

    @OneToMany(mappedBy = "shop")
    @JsonManagedReference
    private List<Queue> queues;

    @OneToOne(mappedBy = "ownedShop")
    @JsonBackReference
    private User shopOwner;

}