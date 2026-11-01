package org.amhe.models.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "position_echange")
@Getter
@Setter
public abstract class Coup {
    @Id
    @SequenceGenerator(name = "coup_seq", sequenceName = "COUP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coup_seq")
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "echange_id", referencedColumnName = "id")
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
