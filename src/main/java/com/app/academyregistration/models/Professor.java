package com.app.academyregistration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Professor entity to manage persistence of Professor objects.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    /**
     * Unique id for professor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of the professor
     */
    private String name;
}
