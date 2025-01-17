package org.amhe.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RulesetExpo implements Serializable {
    private Long id;
    private String nom;
    private String description;
    private long timerLimite;
    private Boolean timerReverse;
    private List<RulesetRefExpo> vulnerants;
    private List<RulesetRefExpo> cibles;
}

