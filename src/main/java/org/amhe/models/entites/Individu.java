package org.amhe.models.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Individu {
    @Id
    @SequenceGenerator(name = "individu_seq", sequenceName = "INDIVIDU_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "individu_seq")
    private Long id;
    private String nom;
    private String prenom;
    private String pseudo;
    @ManyToOne
    private Club club;
}
