package org.amhe.models.entites;

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
    @JoinColumn(name = "evenement_id")
//    @JsonIgnoreProperties({"inscrits", "ruleset"})
    private Evenement evenement;
    @ManyToOne
    @JoinColumn(name = "poule_id")
//    @JsonIgnoreProperties({"inscrits", "evenement"})
    private Poule poule;

    @ManyToOne
    @JoinColumn(name = "id_a")
    private Inscrit inscritA;
    @ManyToOne
    @JoinColumn(name = "id_b")
    private Inscrit inscritB;
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
