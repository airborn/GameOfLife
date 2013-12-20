package pl.airborn.gameoflife.rules;

import com.google.common.base.Predicate;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;

public class ShouldDiePredicate implements Predicate<Cell> {
    private final Population currentPopulation;

    public ShouldDiePredicate(Population currentPopulation) {
        this.currentPopulation = currentPopulation;
    }

    @Override
    public boolean apply(Cell cell) {
        Position cellPosition = cell.getPosition();
        int livingNeighbours = currentPopulation.getNumberOfLivingNeighbours(cellPosition);
        return livingNeighbours != 2 && livingNeighbours != 3;
    }
}
