package org.amhe.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CombattantDetailsCoups {
    private int total;
    private List<DetailsCoupsListeElem> cibles;
    private List<DetailsCoupsListeElem> vulnerants;
    private List<DetailsCoupsListeElem> details;
}
