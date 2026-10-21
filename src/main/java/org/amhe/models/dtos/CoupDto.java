package org.amhe.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CoupDto implements Serializable {
    private Long id;
    private Long matchId;
    private InscritDto attaquant;
    private InscritDto defenseur;
    private String attaquantCouleur;
    private String defenseurCouleur;
    private Long attaquantScore;
    private Long defenseurScore;
    private LocalDateTime timecode;
    private boolean doubleAtk;
    private boolean doubleDef;
    private boolean afterblow;
    private boolean simultanee;
    private boolean faute;
    private ItemDto vulnerant;
    private ItemDto cible;
}
