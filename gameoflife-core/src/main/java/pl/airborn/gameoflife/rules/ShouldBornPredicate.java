package pl.airborn.gameoflife.rules;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.PopulationStateChecker;
import pl.airborn.gameoflife.inject.NeighboursRequiredToBorn;
import pl.airborn.gameoflife.position.Position;

@Singleton
public class ShouldBornPredicate implements Predicate<Position> {
    private final Population population;
    private final PopulationStateChecker populationStateChecker;
    private final ImmutableSet<Integer> neighboursRequiredToBorn;

    @Inject
    public ShouldBornPredicate(Population population, PopulationStateChecker populationStateChecker, @NeighboursRequiredToBorn ImmutableSet<Integer> neighboursRequiredToBorn) {
        this.population = population;
        this.populationStateChecker = populationStateChecker;
        this.neighboursRequiredToBorn = neighboursRequiredToBorn;
    }

    @Override
    public boolean apply(Position position) {
        if (population.isAlive(position)) {
            return false;
        }
        int livingNeighbours = populationStateChecker.getNumberOfLivingNeighbours(position);
        return neighboursRequiredToBorn.contains(livingNeighbours);
    }
}
