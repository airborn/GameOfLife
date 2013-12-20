package pl.airborn.gameoflife;

import com.google.common.collect.Sets;

import java.util.Set;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Set<Position> getNeighboursPositions() {
        Set<Position> neighboursPositions = Sets.newHashSet();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                neighboursPositions.add(new Position(x + dx, y + dy));
            }
        }
        neighboursPositions.remove(this);
        return neighboursPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;

        if (x != position.x) {
            return false;
        }
        if (y != position.y) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}