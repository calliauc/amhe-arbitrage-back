package org.amhe.models.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Coup {
    @Id
    @SequenceGenerator(name = "coup_seq", sequenceName = "COUP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coup_seq")
    private Long id;
    @Column(name = "match_id")
    private Long matchId;
    @ManyToOne
    private Inscrit attaquant;
    @ManyToOne
    private Inscrit defenseur;
    //    @Column(name = "attaquant_couleur")
//    private String attaquantCouleur;
//    @Column(name = "defenseur_couleur")
//    private String defenseurCouleur;
    @Column(name = "attaquant_score")
    private Long attaquantScore;
    @Column(name = "defenseur_score")
    private Long defenseurScore;
    private LocalDateTime timecode;
    private boolean doubleAtk;
    private boolean doubleDef;
    private boolean afterblow;
    private boolean simultanee;
    private boolean faute;
    @ManyToOne
    private Vulnerant vulnerant;
    @ManyToOne
    private Cible cible;
}
