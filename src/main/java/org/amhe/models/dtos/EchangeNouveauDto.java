package org.amhe.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EchangeNouveauDto implements Serializable {
    private Long id;
    private Long match;

    private Long type;

    private Long inscritA;
    private Long scoreA;
    private Long vulnerantA;
    private Long cibleA;
    private Long penaliteA;

    private Long inscritB;
    private Long scoreB;
    private Long vulnerantB;
    private Long cibleB;
    private Long penaliteB;
}
