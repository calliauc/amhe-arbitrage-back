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
    @SequenceGenerator(name = "seq", sequenceName = "RULESET_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private String nom;
    private String description;
    private long timerStart;
    private long timerEnd;
    private Boolean timerReverse;
    private List<Long> vulnerants;
    private List<Long> cibles;
}
