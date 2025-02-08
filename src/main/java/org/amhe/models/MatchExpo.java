package org.amhe.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MatchExpo implements Serializable {
    private Long id;
    private Combattant infosA;
    private Combattant infosB;
    private String couleurA;
    private String couleurB;
    private int scoreA;
    private int scoreB;
    private LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int timer;
    private String statut;
    private List<Tag> tags;
    private RulesetExpo ruleset;
}
