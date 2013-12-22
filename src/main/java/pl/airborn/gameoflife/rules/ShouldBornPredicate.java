package pl.airborn.gameoflife.rules;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;

import java.util.Set;

public class ShouldBornPredicate implements Predicate<Position> {
    private final Population population;
    private final Set<Integer> neighboursRequiredToBorn = Sets.newHashSet(3);

    public ShouldBornPredicate(Population population) {
        this.population = population;
    }

    @Override
    public boolean apply(Position position) {
        if (population.isAlive(position)) {
            return false;
        }
        int livingNeighbours = population.getNumberOfLivingNeighbours(position);
        return neighboursRequiredToBorn.contains(livingNeighbours);
    }
}
