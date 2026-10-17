package org.amhe.models.trier;

import lombok.Getter;
import lombok.Setter;
import org.amhe.models.entites.Tag;

import java.util.List;

@Getter
@Setter
public class TagsFiltres {
    private List<Tag> tagsRequis;
    private List<Tag> tagsOptions;
    private List<Tag> tagsExclus;
}
