package org.amhe.models.trier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class CriteresRechercheMatchs implements Serializable {
    private Long idEvenement;
    private Long idInscrit;
    private ArrayList<Long> idTags;
}
