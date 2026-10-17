package org.amhe.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InscritDto implements Serializable {
    private Long id;
    private Long idIndividu;
    private Long idEvenement;
    private String nom;
    private String prenom;
    private String club;
    private String evenement;
}
