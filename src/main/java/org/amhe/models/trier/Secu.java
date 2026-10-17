package org.amhe.models.trier;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Secu {
    @Id
    private String code;
    private String secret;
}