package org.amhe.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TagsFiltres {
    private List<Tag> tagsRequis;
    private List<Tag> tagsOptions;
    private List<Tag> tagsExclus;
}
