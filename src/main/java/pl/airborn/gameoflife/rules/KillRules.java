package pl.airborn.gameoflife.rules;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;

import java.util.Set;

@Singleton
public class KillRules {

    private final ShouldDiePredicate shouldDiePredicate;

    @Inject
    public KillRules(ShouldDiePredicate shouldDiePredicate) {
        this.shouldDiePredicate = shouldDiePredicate;
    }

    public ImmutableSet<Position> getKilled(Population currentPopulation) {
        Set<Position> populationMembersPosition = currentPopulation.getMembersPositions();
        Set<Position> killed = Sets.filter(populationMembersPosition, shouldDiePredicate);
        return ImmutableSet.copyOf(killed);
    }
}
