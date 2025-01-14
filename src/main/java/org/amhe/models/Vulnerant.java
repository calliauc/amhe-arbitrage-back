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
    @SequenceGenerator(name = "seq", sequenceName = "VULNERANT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private String code;
    private String libelle;
}
