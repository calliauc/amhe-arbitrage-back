package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.Poule;
import org.amhe.models.PouleExpo;
import org.amhe.models.Tag;
import org.amhe.repos.CombattantRepo;
import org.amhe.repos.TagRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PouleMapper {
    @Inject
    RulesetMapper rulesetMapper;

    @Inject
    TagRepo tagRepo;

    @Inject
    CombattantRepo combattantRepo;

    public List<Poule> listeExpoVersBase(final List<PouleExpo> poulesExpo) {
        List<Poule> poules = new ArrayList<>();
        poulesExpo.forEach(pouleExpo ->
                poules.add(this.expoVersBase(pouleExpo))
        );
        return poules;
    }

    public List<PouleExpo> listeBaseVersExpo(final List<Poule> poules) {
        List<PouleExpo> poulesExpo = new ArrayList<>();
        poules.forEach(poule ->
                poulesExpo.add(this.baseVersExpo(poule))
        );
        return poulesExpo;
    }

    public Poule expoVersBase(final PouleExpo pouleExpo) {
        Poule poule = new Poule();
        poule.setId(pouleExpo.getId());
        poule.setNom(pouleExpo.getNom());
        poule.setTags(pouleExpo.getTags().stream().map(Tag::getId).collect(Collectors.toSet()));
        return poule;
    }

    public PouleExpo baseVersExpo(final Poule poule) {
        PouleExpo pouleExpo = new PouleExpo();
        pouleExpo.setId(poule.getId());
        pouleExpo.setNom(poule.getNom());
        pouleExpo.setTags(poule.getTags().stream()
                .map(id -> tagRepo.getTagById(id)).collect(Collectors.toSet()));
        return pouleExpo;
    }

}