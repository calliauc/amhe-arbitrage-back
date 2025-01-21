package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Ruleset implements Serializable {
    @Id
    @SequenceGenerator(name = "ruleset_seq", sequenceName = "RULESET_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ruleset_seq")
    private Long id;
    private String nom;
    private String description;
    private long timerLimite;
    private Boolean timerReverse;
    private List<String> vulnerants;
    private List<String> cibles;
}
