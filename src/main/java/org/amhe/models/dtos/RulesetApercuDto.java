package org.amhe.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RulesetApercuDto implements Serializable {
    private String nom;
    private String description;
    private long timerLimite;
    private Boolean timerReverse;
    private List<String> vulnerants;
    private List<String> cibles;
}
