package pl.airborn.gameoflife;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.position.PositionCalculator;

import java.util.Set;

@Singleton
public class PopulationStateChecker {
    @Inject
    private PositionCalculator positionCalculator;
    @Inject
    private Population population;

    public int getNumberOfLivingNeighbours(Position position) {
        Set<Position> neighboursPositions = positionCalculator.getNeighboursPositions(position);
        return Sets.filter(neighboursPositions, isAlive).size();
    }

    private final Predicate<Position> isAlive = new Predicate<Position>() {

        @Override
        public boolean apply(Position position) {
            return population.isAlive(position);
        }
    };
}
