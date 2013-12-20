package pl.airborn.gameoflife.rules;

import com.google.common.base.Predicate;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;

public class ShouldBornPredicate implements Predicate<Position> {
    private final Population population;

    public ShouldBornPredicate(Population population) {
        this.population = population;
    }

    @Override
    public boolean apply(Position position) {
        if (population.isAlive(position)) {
            return false;
        }
        int livingNeighbours = population.getNumberOfLivingNeighbours(position);
        return livingNeighbours == 3;
    }
}
