package pl.airborn.gameoflife;

import com.google.inject.Singleton;
import pl.airborn.gameoflife.position.Position;

@Singleton
public class CellFactory {

    public Cell createCell(Position position) {
        return new Cell(position);
    }
}
