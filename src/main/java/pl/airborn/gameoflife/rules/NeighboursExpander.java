package pl.airborn.gameoflife.rules;

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.position.PositionCalculator;

@Singleton
public class NeighboursExpander implements Function<Position, Iterable<Position>> {

    private final PositionCalculator positionCalculator;

    @Inject
    public NeighboursExpander(PositionCalculator positionCalculator) {
        this.positionCalculator = positionCalculator;
    }

    @Override
    public Iterable<Position> apply(Position position) {
        return positionCalculator.getNeighboursPositions(position);
    }
}
