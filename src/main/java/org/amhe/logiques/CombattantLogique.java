package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.Combattant;
import org.amhe.repos.MatchRepo;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class CombattantLogique {
    @Inject
    MatchRepo matchRepo;

    public Set<Combattant> getCombattantsByMatchTags(final List<Long> tags) {
        return matchRepo.getMatchs().stream()
                .filter(match -> new HashSet<>(match.getTags()).containsAll(tags))
                .map(match -> Arrays.asList(match.getInfosA(), match.getInfosB()))
                .flatMap(Collection::stream).collect(Collectors.toSet());
    }
}
