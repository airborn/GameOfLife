package pl.airborn.gameoflife.position;

import com.google.common.collect.Sets;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Position;

import java.util.Set;

@Singleton
public class PositionCalculator {

    public Set<Position> getNeighboursPositions(Position position) {
        int x = position.getX();
        int y = position.getY();
        Set<Position> neighboursPositions = Sets.newHashSet();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = x + dx;
                int newY = y + dy;
                neighboursPositions.add(new Position(newX, newY));
            }
        }
        neighboursPositions.remove(position);
        return neighboursPositions;
    }
}
