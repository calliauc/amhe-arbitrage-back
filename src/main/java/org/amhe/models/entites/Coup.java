package org.amhe.models.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "inscrit")
@Getter
@Setter
public class Coup {
    @Id
    @SequenceGenerator(name = "coup_seq", sequenceName = "COUP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coup_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "echange_id")
    private Echange echange;

    @ManyToOne
    @JoinColumn(name = "inscrit_id")
    private Inscrit inscrit;
    @ManyToOne
    @JoinColumn(name = "opposant_id")
    private Inscrit opposant;

    @ManyToOne
    private Item vulnerant;
    @ManyToOne
    private Item cible;

    @ManyToOne
    private Item penalite;

}
