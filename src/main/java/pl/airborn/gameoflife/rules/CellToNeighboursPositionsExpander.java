package pl.airborn.gameoflife.rules;

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.position.PositionCalculator;

@Singleton
public class CellToNeighboursPositionsExpander implements Function<Cell, Iterable<Position>> {

    private final PositionCalculator positionCalculator;

    @Inject
    public CellToNeighboursPositionsExpander(PositionCalculator positionCalculator) {
        this.positionCalculator = positionCalculator;
    }

    @Override
    public Iterable<Position> apply(Cell cell) {
        Position position = cell.getPosition();
        return positionCalculator.getNeighboursPositions(position);
    }
}
