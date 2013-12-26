package pl.airborn.gameoflife.rules;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.PopulationStateChecker;
import pl.airborn.gameoflife.Position;

import java.util.Set;

@Singleton
public class ShouldBornPredicate implements Predicate<Position> {
    private final PopulationStateChecker populationStateChecker;
    private final Set<Integer> neighboursRequiredToBorn = Sets.newHashSet(3);

    @Inject
    public ShouldBornPredicate(PopulationStateChecker populationStateChecker) {
        this.populationStateChecker = populationStateChecker;
    }

    @Override
    public boolean apply(Position position) {
        if (populationStateChecker.isAlive(position)) {
            return false;
        }
        int livingNeighbours = populationStateChecker.getNumberOfLivingNeighbours(position);
        return neighboursRequiredToBorn.contains(livingNeighbours);
    }
}
