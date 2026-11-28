package org.amhe.models.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity(name = "ITEM")
@Getter
@Setter
public class Item implements Serializable {
    @Id
    @SequenceGenerator(name = "item_seq", sequenceName = "ITEM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    private Long id;
    private String type;
    private String code;
    private String libelle;

    private List<String> flags;

    public boolean estType(String type) {
        return code.equalsIgnoreCase(this.type);
    }

    public boolean estCode(String code) {
        return code.equalsIgnoreCase(this.code);
    }
}
