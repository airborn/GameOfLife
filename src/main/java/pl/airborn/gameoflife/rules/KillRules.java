package pl.airborn.gameoflife.rules;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;

import java.util.Set;

@Singleton
public class KillRules {

    private final ShouldDiePredicate shouldDiePredicate;

    @Inject
    public KillRules(ShouldDiePredicate shouldDiePredicate) {
        this.shouldDiePredicate = shouldDiePredicate;
    }

    public Set<Cell> getKilled(Population currentPopulation) {
        Set<Cell> populationMembers = currentPopulation.getMembers();
        return Sets.filter(populationMembers, shouldDiePredicate);
    }
}
