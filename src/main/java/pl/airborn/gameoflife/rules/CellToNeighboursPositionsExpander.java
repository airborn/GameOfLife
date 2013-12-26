package pl.airborn.gameoflife.rules;

import com.google.common.base.Function;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Position;

@Singleton
public class CellToNeighboursPositionsExpander implements Function<Cell, Iterable<Position>> {

    @Override
    public Iterable<Position> apply(Cell cell) {
        return cell.getNeighboursPositions();
    }
}
