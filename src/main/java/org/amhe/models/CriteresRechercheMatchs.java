package org.amhe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class CriteresRechercheMatchs {
    private Long idCombattant;
    private ArrayList<Long> idTags;
}
