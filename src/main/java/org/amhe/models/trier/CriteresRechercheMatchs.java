package org.amhe.models.trier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CriteresRechercheMatchs implements Serializable {
    private Long idEvenement;
    private Long idInscrit;
    private Long idPoule;
}
