package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.InscritDto;
import org.amhe.models.entites.Inscrit;
import org.amhe.repos.EvenementRepo;
import org.amhe.repos.IndividuRepo;

import java.util.*;

@ApplicationScoped
public class InscritMapper {
    @Inject
    EvenementRepo evenementRepo;

    @Inject
    IndividuRepo individuRepo;

    public Set<Inscrit> listeDtoVersBase(final Set<InscritDto> inscritsDto) {
        Set<Inscrit> inscrits = new HashSet<>();
        if (Objects.nonNull(inscritsDto))
            inscritsDto.forEach(inscritDto -> inscrits.add(this.dtoVersBase(inscritDto)));
        return inscrits;
    }

    public List<Inscrit> listeDtoVersBase(final List<InscritDto> inscritsDto) {
        List<Inscrit> inscrits = new ArrayList<>();
        if (Objects.nonNull(inscritsDto))
            inscritsDto.forEach(inscritDto -> inscrits.add(this.dtoVersBase(inscritDto)));
        return inscrits;
    }

    public Set<InscritDto> listeBaseVersDto(final Set<Inscrit> inscrits) {
        Set<InscritDto> inscritsDto = new HashSet<>();
        if (Objects.nonNull(inscrits))
            inscrits.forEach(inscrit -> inscritsDto.add(this.baseVersDto(inscrit)));
        return inscritsDto;
    }

    public List<InscritDto> listeBaseVersDto(final List<Inscrit> inscrits) {
        List<InscritDto> inscritsDto = new ArrayList<>();
        if (Objects.nonNull(inscrits))
            inscrits.forEach(inscrit -> inscritsDto.add(this.baseVersDto(inscrit)));
        return inscritsDto;
    }

    public Inscrit dtoVersBase(final InscritDto inscritDto) {
        Inscrit inscrit = new Inscrit();
        inscrit.setId(inscritDto.getId());
        inscrit.setEvenement(evenementRepo.getEvenementById(inscritDto.getIdEvenement()));
        inscrit.setIndividu(individuRepo.getIndividuById(inscritDto.getIdIndividu()));
        return inscrit;
    }

    public InscritDto baseVersDto(final Inscrit inscrit) {
        InscritDto inscritDto = new InscritDto();
        inscritDto.setId(inscrit.getId());
        inscritDto.setIdEvenement(inscrit.getEvenement().getId());
        inscritDto.setEvenement(inscrit.getEvenement().getNom());
        inscritDto.setIdIndividu(inscrit.getIndividu().getId());
        inscritDto.setNom(inscrit.getIndividu().getNom());
        inscritDto.setPrenom(inscrit.getIndividu().getPrenom());
        inscritDto.setClub(inscrit.getIndividu().getClub().getNomCourt());
        return inscritDto;
    }

}

