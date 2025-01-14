package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Match implements Serializable {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "MATCH_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    @ManyToOne
    private Combattant bleu;
    @ManyToOne
    private Combattant rouge;
    private int scoreBleu;
    private int scoreRouge;
    private long timerStart;
    private long timerEnd;
    private Boolean timerReverse;
}
