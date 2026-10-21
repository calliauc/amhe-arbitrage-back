package org.amhe.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RulesetDto implements Serializable {
    private Long id;
    private String nom;
    private String description;
    private long timerLimite;
    private Boolean timerReverse;
    private List<ItemDto> vulnerants;
    private List<ItemDto> cibles;
}

