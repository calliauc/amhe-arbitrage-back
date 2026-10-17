package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.EvenementDto;
import org.amhe.models.entites.Evenement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class EvenementMapper {
    @Inject
    InscritMapper inscritMapper;

    @Inject
    RulesetMapper rulesetMapper;

//    public List<Evenement> listeDtoVersBase(final List<EvenementDto> evenementsDto) {
//        List<Evenement> evenements = new ArrayList<>();
//        if (Objects.nonNull(evenementsDto))
//            evenementsDto.forEach(evenementDto -> evenements.add(this.dtoVersBase(evenementDto)));
//        return evenements;
//    }

    public List<EvenementDto> listeBaseVersDto(final List<Evenement> evenements) {
        List<EvenementDto> evenementsDto = new ArrayList<>();
        if (Objects.nonNull(evenements))
            evenements.forEach(evenement -> evenementsDto.add(this.baseVersDto(evenement)));
        return evenementsDto;
    }

//    public Evenement dtoVersBase(final EvenementDto evenementDto) {
//        Evenement evenement = new Evenement();
//        if (Objects.nonNull(evenementDto.getRuleset())) {
//            evenement.setId(evenementDto.getId());
//            evenement.setNom(evenementDto.getNom());
//            evenement.setDescription(evenementDto.getDescription());
//            evenement.setInscrits(inscritMapper.listeDtoVersBase(evenementDto.getInscrits()));
//            evenement.setRuleset(rulesetMapper.dtoVersBase(evenementDto.getRuleset()));
//        }
//        return evenement;
//    }
    
    public EvenementDto baseVersDto(final Evenement evenement) {
        EvenementDto evenementDto = new EvenementDto();
        evenementDto.setId(evenement.getId());
        evenementDto.setNom(evenement.getNom());
        evenementDto.setDescription(evenement.getDescription());
        evenementDto.setDateDebut(evenement.getDateDebut());
        evenementDto.setDateFin(evenement.getDateFin());
        evenementDto.setInscrits(inscritMapper.listeBaseVersDto(evenement.getInscrits()));
        evenementDto.setRuleset(rulesetMapper.baseVersApercuDto(evenement.getRuleset()));
        return evenementDto;
    }

}