package org.amhe.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MatchDto implements Serializable {
    private Long id;
    @JsonIgnoreProperties({"inscrits", "ruleset"})
    private EvenementDto evenement;
    @JsonIgnoreProperties({"inscrits", "evenement", "matchs"})
    private PouleDto poule;
    private InscritDto inscritA;
    private InscritDto inscritB;
    private int scoreA;
    private int scoreB;

    private LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String statut;
    private int timer;
    private List<ItemDto> tags;
    private RulesetDto ruleset;
}
