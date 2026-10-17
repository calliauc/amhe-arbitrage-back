package org.amhe.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.amhe.models.entites.Evenement;
import org.amhe.models.entites.Poule;
import org.amhe.models.entites.Ruleset;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MatchDto implements Serializable {
    private Long id;
    @JsonIgnoreProperties({"inscrits", "ruleset"})
    private Evenement evenement;
    @JsonIgnoreProperties({"inscrits", "evenement"})
    private Poule poule;
    private InscritDto inscritA;
    private InscritDto inscritB;
    private int scoreA;
    private int scoreB;

    private LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String statut;
    private int timer;
    private List<Long> tags;
    private Ruleset ruleset;
}
