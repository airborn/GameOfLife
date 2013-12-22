package pl.airborn.gameoflife.rules;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;

import java.util.Set;

public class ShouldDiePredicate implements Predicate<Cell> {
    private final Population currentPopulation;
    private final Set<Integer> neighboursRequiredToSurvive = Sets.newHashSet(2, 3);

    public ShouldDiePredicate(Population currentPopulation) {
        this.currentPopulation = currentPopulation;
    }

    @Override
    public boolean apply(Cell cell) {
        Position cellPosition = cell.getPosition();
        int livingNeighbours = currentPopulation.getNumberOfLivingNeighbours(cellPosition);
        return !neighboursRequiredToSurvive.contains(livingNeighbours);
    }
}
