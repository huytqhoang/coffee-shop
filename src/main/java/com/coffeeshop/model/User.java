package com.coffeeshop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // Values: "OWNER" or "OPERATOR" or "CUSTOMER"

    @ManyToOne
    @JoinColumn(name = "shop_id_owner", referencedColumnName = "id")
    @JsonManagedReference
    private Shop ownedShop; // Relationship with shops where the role is "OWNER"

}