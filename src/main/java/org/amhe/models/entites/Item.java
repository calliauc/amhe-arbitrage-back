package org.amhe.models.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "RULESET_REF")
@Getter
@Setter
public class Item implements Serializable {
    @Id
    @SequenceGenerator(name = "ruleset_ref_seq", sequenceName = "RULESET_REF_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ruleset_ref_seq")
    private Long id;
    private String code;
    private String type;
    private String libelle;
}
