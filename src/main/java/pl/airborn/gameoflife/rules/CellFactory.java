package pl.airborn.gameoflife.rules;

import com.google.common.base.Function;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Position;

public class CellFactory implements Function<Position, Cell> {

    @Override
    public Cell apply(Position position) {
        return new Cell(position);
    }
}
