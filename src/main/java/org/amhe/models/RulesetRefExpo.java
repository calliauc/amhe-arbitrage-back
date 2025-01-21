package org.amhe.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonDeserialize
public class RulesetRefExpo implements Serializable {
    private String code;
    private String libelle;
    private boolean checked;
}
