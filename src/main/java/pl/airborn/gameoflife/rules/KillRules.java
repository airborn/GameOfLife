package pl.airborn.gameoflife.rules;

import com.google.common.collect.Collections2;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;

import java.util.Collection;

@Singleton
public class KillRules {

    private final ShouldDiePredicate shouldDiePredicate;

    @Inject
    public KillRules(ShouldDiePredicate shouldDiePredicate) {
        this.shouldDiePredicate = shouldDiePredicate;
    }

    public Collection<Cell> getKilled(Population currentPopulation) {
        Collection<Cell> populationMembers = currentPopulation.getMembers();
        return Collections2.filter(populationMembers, shouldDiePredicate);
    }
}
