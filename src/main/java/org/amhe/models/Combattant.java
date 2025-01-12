package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Combattant {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "COMBATTANT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private String nom;
    private String prenom;
    private String pseudo;
    @ManyToOne
    private Club club;
}
