package org.amhe.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class CreationEvenementDto implements Serializable {
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private RulesetDto ruleset;
}
