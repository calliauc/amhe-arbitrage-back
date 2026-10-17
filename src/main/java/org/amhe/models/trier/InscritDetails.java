package org.amhe.models.trier;

import lombok.Getter;
import lombok.Setter;
import org.amhe.models.dtos.MatchDto;
import org.amhe.models.entites.Inscrit;

import java.util.List;

@Getter
@Setter
public class InscritDetails {
    private Inscrit inscrit;
    private List<MatchDto> matchs;
    private TagsFiltres tags;
    private int pointsMarques;
    private int pointsPerdus;
    private InscritDetailsCoups coupsSubis;
    private InscritDetailsCoups coupsMarques;
}
