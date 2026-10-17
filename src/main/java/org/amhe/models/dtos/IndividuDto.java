package org.amhe.models.dtos;

import lombok.Getter;
import lombok.Setter;
import org.amhe.models.entites.Club;

import java.io.Serializable;

@Getter
@Setter
public class IndividuDto implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String pseudo;
    private Club club;
}
