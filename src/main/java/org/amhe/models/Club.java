package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Club {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "CLUB_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private String nomCourt;
    private String nomComplet;
    private String ville;
}