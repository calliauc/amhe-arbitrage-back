package org.amhe.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CombattantDetailsCoups {
    private int total;
    private Set<DetailsCoupsListeElem> cibles;
    private Set<DetailsCoupsListeElem> vulnerants;
    private Set<DetailsCoupsListeElem> details;
}
