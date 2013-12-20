package pl.airborn.gameoflife;

import java.util.Objects;
import java.util.Set;

public class Cell {

    private final Position position;

    public Cell(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Set<Position> getNeighboursPositions() {
        return position.getNeighboursPositions();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        return Objects.equals(position, cell.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "position=" + position +
                '}';
    }
}
