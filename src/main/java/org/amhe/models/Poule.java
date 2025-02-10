package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Poule {
    @Id
    @SequenceGenerator(name = "poule_seq", sequenceName = "POULE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poule_seq")
    private Long id;
    private String nom;
    private Set<Long> tags;
}