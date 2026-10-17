package org.amhe.models.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Cible implements Serializable {
    @Id
    private String code;
    private String libelle;
}
