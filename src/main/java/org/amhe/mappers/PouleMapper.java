package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.logiques.EvenementLogique;
import org.amhe.models.dtos.PouleDto;
import org.amhe.models.entites.Poule;
import org.amhe.models.entites.Tag;
import org.amhe.repos.TagRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class PouleMapper {
    @Inject
    TagRepo tagRepo;

    @Inject
    InscritMapper inscritMapper;

    @Inject
    MatchMapper matchMapper;

    @Inject
    EvenementMapper evenementMapper;

    @Inject
    EvenementLogique evenementLogique;

    public List<Poule> listeDtoVersBase(final List<PouleDto> poulesDto) {
        List<Poule> poules = new ArrayList<>();
        if (!poulesDto.isEmpty())
            poulesDto.forEach(pouleDto -> poules.add(this.dtoVersBase(pouleDto)));
        return poules;
    }

    public List<PouleDto> listeBaseVersDto(final List<Poule> poules) {
        List<PouleDto> poulesDto = new ArrayList<>();
        if (!poules.isEmpty())
            poules.forEach(poule -> poulesDto.add(this.baseVersDto(poule)));
        return poulesDto;
    }

    public Poule dtoVersBase(final PouleDto pouleDto) {
        Poule poule = new Poule();
        poule.setId(pouleDto.getId());
        poule.setNom(pouleDto.getNom());
        poule.setDescription(pouleDto.getDescription());
        poule.setEvenement(evenementLogique.getEvenementById(pouleDto.getEvenement().getId()));
        poule.setInscrits(inscritMapper.listeDtoVersBase(pouleDto.getInscrits()));
        poule.setMatchs(matchMapper.listeDtoVersBase(pouleDto.getMatchs()));
        if (Objects.nonNull(pouleDto.getTags()) && !pouleDto.getTags().isEmpty())
            poule.setTags(pouleDto.getTags().stream().map(Tag::getId).collect(Collectors.toSet()));
        return poule;
    }

    public PouleDto baseVersDto(final Poule poule) {
        PouleDto pouleDto = new PouleDto();
        pouleDto.setId(poule.getId());
        pouleDto.setNom(poule.getNom());
        pouleDto.setDescription(poule.getDescription());
        pouleDto.setEvenement(evenementMapper.baseVersDto(poule.getEvenement()));
        pouleDto.setInscrits(inscritMapper.listeBaseVersDto(poule.getInscrits()));
        pouleDto.setMatchs(matchMapper.listeBaseVersDto(poule.getMatchs()));
        if (Objects.nonNull(poule.getTags()))
            pouleDto.setTags(poule.getTags().stream()
                    .map(id -> tagRepo.getTagById(id))
                    .collect(Collectors.toSet()));
        return pouleDto;
    }

    public Poule dtoVersBaseId(final PouleDto pouleDto) {
        Poule poule = new Poule();
        poule.setId(pouleDto.getId());
        return poule;
    }

}