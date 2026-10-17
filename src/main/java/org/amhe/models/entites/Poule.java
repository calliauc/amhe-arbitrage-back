package org.amhe.models.entites;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
public class Poule implements Serializable {
    @Id
    @SequenceGenerator(name = "poule_seq", sequenceName = "POULE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poule_seq")
    private Long id;
    private String nom;
    private String description;
    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;
    @ManyToMany(targetEntity = Inscrit.class, fetch = FetchType.EAGER)
    private Set<Inscrit> inscrits;
    @Nullable
    @OneToMany(targetEntity = Match.class, fetch = FetchType.EAGER)
    private Set<Match> matchs;
    @Nullable
    private Set<Long> tags;
}