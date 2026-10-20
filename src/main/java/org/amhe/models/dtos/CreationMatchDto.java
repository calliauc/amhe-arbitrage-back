package org.amhe.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CreationMatchDto implements Serializable {
    private EvenementDto evenement;
    private PouleDto poule;
    private InscritDto inscritA;
    private InscritDto inscritB;
    private int timer;
    private RulesetDto ruleset;
    private List<Long> tags;
}
