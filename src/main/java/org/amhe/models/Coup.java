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
    @SequenceGenerator(name = "coup_seq", sequenceName = "COUP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coup_seq")
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
    @Column(name = "attaquant_score")
    private Long attaquantScore;
    @Column(name = "defenseur_score")
    private Long defenseurScore;
    private boolean doubleTouche;
    @ManyToOne
    private Vulnerant vulnerant;
    @ManyToOne
    private Cible cible;
}
