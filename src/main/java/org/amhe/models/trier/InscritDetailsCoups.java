package org.amhe.models.trier;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class InscritDetailsCoups {
    private int total;
    private Set<DetailsCoupsListeElem> cibles;
    private Set<DetailsCoupsListeElem> vulnerants;
    private Set<DetailsCoupsListeElem> details;
}
