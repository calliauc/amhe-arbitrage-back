package org.amhe.models.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "ITEM")
@Getter
@Setter
public class Item implements Serializable {
    @Id
    @SequenceGenerator(name = "item_seq", sequenceName = "ITEM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    private Long id;
    private String code;
    private String type;
    private String libelle;
}
