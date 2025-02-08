package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Tag implements Serializable {
    @Id
    @SequenceGenerator(name = "tag_seq", sequenceName = "TAG_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_seq")
    private Long id;
    private String code;
}
