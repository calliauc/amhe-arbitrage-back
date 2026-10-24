package org.amhe.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EvenementDto implements Serializable {
    private Long id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    //    @JsonIgnoreProperties(value = "evenement")
    private List<InscritDto> inscrits;
    private RulesetApercuDto ruleset;
}
