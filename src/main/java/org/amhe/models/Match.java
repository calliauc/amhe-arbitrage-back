package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Match implements Serializable {
    @Id
    @SequenceGenerator(name = "match_seq", sequenceName = "MATCH_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_a")
    private Combattant infosA;
    @ManyToOne
    @JoinColumn(name = "id_b")
    private Combattant infosB;
    @Column(name = "couleur_a")
    private String couleurA;
    @Column(name = "couleur_b")
    private String couleurB;
    @Column(name = "score_a")
    private int scoreA;
    @Column(name = "score_b")
    private int scoreB;
    private LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String statut;
    private int timer;
    private List<Long> tags;
    private Ruleset ruleset;
}
