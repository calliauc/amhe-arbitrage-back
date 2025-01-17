package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Vulnerant implements Serializable {
    @Id
    @SequenceGenerator(name = "vulnerant_seq", sequenceName = "VULNERANT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vulnerant_seq")
    private Long id;
    private String code;
    private String libelle;
}
