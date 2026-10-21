package org.amhe.models.trier;

import lombok.Getter;
import lombok.Setter;
import org.amhe.models.entites.Item;

import java.util.List;

@Getter
@Setter
public class TagsFiltres {
    private List<Item> tagsRequis;
    private List<Item> tagsOptions;
    private List<Item> tagsExclus;
}
