package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Coup implements Serializable {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "COUP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    @Column(name = "match_id")
    private Long matchId;
    @ManyToOne
    private Combattant attaquant;
    @ManyToOne
    private Combattant defenseur;
    @Column(name = "attaquant_couleur")
    private String attaquantCouleur;
    @Column(name = "defenseur_couleur")
    private String defenseurCouleur;
    @ManyToOne
    private Vulnerant vulnerant;
    @ManyToOne
    private Cible cible;
}
