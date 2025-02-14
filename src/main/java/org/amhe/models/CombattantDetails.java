package org.amhe.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CombattantDetails {
    private Combattant combattant;
    private List<MatchExpo> matchs;
    private TagsFiltres tags;
    private int pointsMarques;
    private int pointsPerdus;
    private CombattantDetailsCoups coupsSubis;
    private CombattantDetailsCoups coupsMarques;
}
