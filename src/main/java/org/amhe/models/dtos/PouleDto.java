package org.amhe.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PouleDto implements Serializable {
    private Long id;
    private String nom;
    private String description;
    private String prefixe;
    @JsonIgnoreProperties(value = {"inscrits", "ruleset"})
    private EvenementDto evenement;
    @JsonIgnoreProperties(value = {"evenement"})
    private List<InscritDto> inscrits;
    @JsonIgnoreProperties(value = {"poule.evenement", "evenement.inscrits"})
    private List<MatchDto> matchs;
    private List<ItemDto> tags;
}