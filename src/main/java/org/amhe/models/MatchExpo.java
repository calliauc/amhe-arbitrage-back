package org.amhe.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MatchExpo implements Serializable {
    private Long id;
    private Combattant infosA;
    private Combattant infosB;
    private String couleurA;
    private String couleurB;
    private int scoreA;
    private int scoreB;
    private int timer;
    private RulesetExpo ruleset;
}
