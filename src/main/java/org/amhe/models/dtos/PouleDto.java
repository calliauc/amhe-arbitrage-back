package org.amhe.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.amhe.models.entites.Tag;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class PouleDto implements Serializable {
    private Long id;
    private String nom;
    private String description;
    @JsonIgnoreProperties(value = {"inscrits", "ruleset", "inscrits"})
    private EvenementDto evenement;
    @JsonIgnoreProperties(value = {"nomEvenement"})
    private Set<InscritDto> inscrits;
    private Set<MatchDto> matchs;
    private Set<Tag> tags;
}