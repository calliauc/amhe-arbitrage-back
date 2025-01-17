package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Cible implements Serializable {
    @Id
    @SequenceGenerator(name = "cible_seq", sequenceName = "CIBLE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cible_seq")
    private Long id;
    private String code;
    private String libelle;
}
