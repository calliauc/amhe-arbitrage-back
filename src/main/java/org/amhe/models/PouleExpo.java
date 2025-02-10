package org.amhe.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PouleExpo {
    private Long id;
    private String nom;
    private Set<Tag> tags;
}