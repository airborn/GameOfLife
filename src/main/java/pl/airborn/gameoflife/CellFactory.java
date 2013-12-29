package pl.airborn.gameoflife;

import com.google.inject.Singleton;

@Singleton
public class CellFactory {

    public Cell createCell(Position position) {
        return new Cell(position);
    }
}
