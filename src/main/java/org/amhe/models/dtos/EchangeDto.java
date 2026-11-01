package org.amhe.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class EchangeDto implements Serializable {
    private Long id;

    @JsonIncludeProperties(value = {"id", "evenement"})
    private MatchDto match;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:s")
    private LocalDateTime timecode;
    private ItemDto type;

    private InscritDto inscritA;
    private ItemDto vulnerantA;
    private ItemDto cibleA;
    private Long scoreA;
    private ItemDto penaliteA;

    private InscritDto inscritB;
    private ItemDto vulnerantB;
    private ItemDto cibleB;
    private Long scoreB;
    private ItemDto penaliteB;
}
