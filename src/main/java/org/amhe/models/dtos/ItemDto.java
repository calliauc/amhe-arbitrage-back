package org.amhe.models.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonDeserialize
//@JsonPropertyOrder({"id", type})
public class ItemDto implements Serializable {
    private Long id;
    private String type;
    private String code;
    private String libelle;
    private boolean checked;
}
