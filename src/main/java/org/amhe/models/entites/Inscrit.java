package org.amhe.models.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Inscrit implements Serializable {
    @Id
    @SequenceGenerator(name = "inscrit_seq", sequenceName = "INSCRIT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inscrit_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;
    @ManyToOne
    @JoinColumn(name = "individu_id")
    private Individu individu;
    // stats ?


    public Inscrit(Evenement evenement, Individu individu) {
        this.evenement = evenement;
        this.individu = individu;
    }
}
