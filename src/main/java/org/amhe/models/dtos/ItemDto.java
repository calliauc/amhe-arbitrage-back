package org.amhe.models.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonDeserialize
public class ItemDto implements Serializable {
    private Long id;
    private String type;
    private String code;
    private String libelle;

    private List<String> flags;
}
