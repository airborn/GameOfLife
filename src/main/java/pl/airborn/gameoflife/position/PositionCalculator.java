package pl.airborn.gameoflife.position;

import java.util.Set;

public interface PositionCalculator<T extends Position> {
    Set<Position> getNeighboursPositions(T position);
}
