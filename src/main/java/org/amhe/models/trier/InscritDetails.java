package org.amhe.models.trier;

import lombok.Getter;
import lombok.Setter;
import org.amhe.models.dtos.InscritDto;
import org.amhe.models.entites.Match;

import java.util.List;

@Getter
@Setter
public class InscritDetails {
    private InscritDto inscrit;
    private int nbMatchs;
    private List<Match> matchs;
//    private InscritDetailsCoups coupsSubis;
//    private InscritDetailsCoups coupsMarques;
}
