package org.amhe.models.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Evenement implements Serializable {
    @Id
    @SequenceGenerator(name = "evenement_seq", sequenceName = "EVENEMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evenement_seq")
    private Long id;
    private String nom;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @OneToMany(targetEntity = Inscrit.class, mappedBy = "evenement", fetch = FetchType.EAGER)
    private List<Inscrit> inscrits;
    @ManyToOne
    @JoinColumn(name = "ruleset_id")
    private Ruleset ruleset;
}
