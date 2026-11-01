package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.logiques.InscritLogique;
import org.amhe.logiques.MatchLogique;
import org.amhe.models.dtos.EchangeDto;
import org.amhe.models.dtos.EchangeNouveauDto;
import org.amhe.models.entites.Echange;
import org.amhe.repos.ItemRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class EchangeMapper {
    @Inject
    InscritLogique inscritLogique;

    @Inject
    InscritMapper inscritMapper;

    @Inject
    MatchLogique matchLogique;

    @Inject
    MatchMapper matchMapper;

    @Inject
    ItemRepo itemRepo;

    @Inject
    ItemMapper itemMapper;

    public List<Echange> listeDtoVersBase(final List<EchangeNouveauDto> echangesDto) {
        List<Echange> echanges = new ArrayList<>();
        if (Objects.nonNull(echangesDto))
            echangesDto.forEach(echangeDto -> echanges.add(this.dtoVersBase(echangeDto)));
        return echanges;
    }

    public List<EchangeDto> listeBaseVersDto(final List<Echange> echanges) {
        List<EchangeDto> echangesDto = new ArrayList<>();
        if (Objects.nonNull(echanges))
            echanges.forEach(echange -> echangesDto.add(this.baseVersDto(echange)));
        return echangesDto;
    }

    public Echange dtoVersBase(final EchangeNouveauDto echangeDto) {
        Echange echange = new Echange();
        echange.setMatch(matchLogique.getMatchById(echangeDto.getMatch()));
        echange.setInscritA(inscritLogique.getInscritById(echangeDto.getInscritA()));
        echange.setInscritB(inscritLogique.getInscritById(echangeDto.getInscritB()));
        echange.setScoreA(echangeDto.getScoreA());
        echange.setScoreB(echangeDto.getScoreB());
        return echange;
    }

    public EchangeDto baseVersDto(final Echange echange) {
        EchangeDto echangeDto = new EchangeDto();
        echangeDto.setId(echange.getId());
        echangeDto.setMatch(matchMapper.baseVersDto(echange.getMatch()));
        echangeDto.setTimecode(echange.getTimecode());
        echangeDto.setType(itemMapper.baseVersDto(echange.getType()));

        echangeDto.setInscritA(inscritMapper.baseVersDto(echange.getInscritA()));
        echangeDto.setScoreA(echange.getScoreA());
        echangeDto.setCibleA(itemMapper.baseVersDto(echange.getCoupA().getCible()));
        echangeDto.setVulnerantA(itemMapper.baseVersDto(echange.getCoupA().getVulnerant()));
        echangeDto.setPenaliteA(itemMapper.baseVersDto(echange.getCoupA().getPenalite()));

        echangeDto.setInscritB(inscritMapper.baseVersDto(echange.getInscritB()));
        echangeDto.setScoreB(echange.getScoreB());
        echangeDto.setCibleB(itemMapper.baseVersDto(echange.getCoupB().getCible()));
        echangeDto.setVulnerantB(itemMapper.baseVersDto(echange.getCoupB().getVulnerant()));
        echangeDto.setPenaliteB(itemMapper.baseVersDto(echange.getCoupB().getPenalite()));

        return echangeDto;
    }

}