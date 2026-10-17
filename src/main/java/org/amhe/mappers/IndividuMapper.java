package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.IndividuDto;
import org.amhe.models.entites.Individu;
import org.amhe.repos.IndividuRepo;

import java.util.*;

@ApplicationScoped
public class IndividuMapper {
    @Inject
    IndividuRepo individuRepo;

    public Set<Individu> listeDtoVersBase(final Set<IndividuDto> individusDto) {
        Set<Individu> individus = new HashSet<>();
        if (Objects.nonNull(individusDto))
            individusDto.forEach(individuDto -> individus.add(this.dtoVersBase(individuDto)));
        return individus;
    }

    public List<Individu> listeDtoVersBase(final List<IndividuDto> individusDto) {
        List<Individu> individus = new ArrayList<>();
        if (Objects.nonNull(individusDto))
            individusDto.forEach(individuDto -> individus.add(this.dtoVersBase(individuDto)));
        return individus;
    }

    public Set<IndividuDto> listeBaseVersDto(final Set<Individu> individus) {
        Set<IndividuDto> individusDto = new HashSet<>();
        if (Objects.nonNull(individus))
            individus.forEach(individu -> individusDto.add(this.baseVersDto(individu)));
        return individusDto;
    }

    public List<IndividuDto> listeBaseVersDto(final List<Individu> individus) {
        List<IndividuDto> individusDto = new ArrayList<>();
        if (Objects.nonNull(individus))
            individus.forEach(individu -> individusDto.add(this.baseVersDto(individu)));
        return individusDto;
    }

    public Individu dtoVersBase(final IndividuDto individuDto) {
        Individu individu = new Individu();
        individu.setId(individuDto.getId());
        individu.setNom(individuDto.getNom());
        individu.setPrenom(individuDto.getPrenom());
        individu.setPseudo(individuDto.getPseudo());
        individu.setClub(individuDto.getClub());
        return individu;
    }

    public IndividuDto baseVersDto(final Individu individu) {
        IndividuDto individuDto = new IndividuDto();
        individuDto.setId(individu.getId());
        individuDto.setNom(individu.getNom());
        individuDto.setPrenom(individu.getPrenom());
        individuDto.setPseudo(individu.getPseudo());
        individuDto.setClub(individu.getClub());
        return individuDto;
    }
}
