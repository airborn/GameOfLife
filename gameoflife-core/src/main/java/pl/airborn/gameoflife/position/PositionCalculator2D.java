package pl.airborn.gameoflife.position;

import com.google.common.collect.Sets;
import com.google.inject.Singleton;

import java.util.Set;

@Singleton
public class PositionCalculator2D implements PositionCalculator<Position2D> {

    @Override
    public Set<Position> getNeighboursPositions(Position2D position) {
        int x = position.getX();
        int y = position.getY();
        Set<Position> neighboursPositions = Sets.newHashSet();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = x + dx;
                int newY = y + dy;
                neighboursPositions.add(new Position2D(newX, newY));
            }
        }
        neighboursPositions.remove(position);
        return neighboursPositions;
    }
}
