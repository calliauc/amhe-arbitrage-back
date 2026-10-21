package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.logiques.EvenementLogique;
import org.amhe.models.dtos.PouleDto;
import org.amhe.models.entites.Poule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PouleMapper {
    @Inject
    ItemMapper itemMapper;

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
        poule.setTags(itemMapper.recupererIds(pouleDto.getTags()));
        return poule;
    }

    public PouleDto baseVersDto(final Poule poule) {
        PouleDto pouleDto = new PouleDto();
        if (Objects.nonNull(poule)) {
            pouleDto.setId(poule.getId());
            pouleDto.setNom(poule.getNom());
            pouleDto.setDescription(poule.getDescription());
            pouleDto.setEvenement(evenementMapper.baseVersDto(poule.getEvenement()));
            pouleDto.setInscrits(inscritMapper.listeBaseVersDto(poule.getInscrits()));
            pouleDto.setMatchs(matchMapper.listeBaseVersDto(poule.getMatchs()));
            pouleDto.setTags(itemMapper.recupererItems(poule.getTags()));
        }
        return pouleDto;
    }

    public Poule dtoVersBaseId(final PouleDto pouleDto) {
        if (Objects.isNull(pouleDto))
            return null;
        Poule poule = new Poule();
        poule.setId(pouleDto.getId());
        return poule;
    }

}