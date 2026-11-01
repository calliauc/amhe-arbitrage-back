package org.amhe.models.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:s")
    private LocalDateTime dateCreation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:s")
    private LocalDateTime dateDebut;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:s")
    private LocalDateTime dateFin;
    private String statut;
    private int timer;
    private List<Long> tags;
    @ManyToOne
    @JoinColumn(name = "id_ruleset")
    private Ruleset ruleset;
}
