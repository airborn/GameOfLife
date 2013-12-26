package pl.airborn.gameoflife;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.position.PositionCalculator;

import java.util.Map;
import java.util.Set;

@Singleton
public class Population implements PopulationStateChecker {

    private final PositionCalculator positionCalculator;
    private final Map<Position, Cell> currentPopulation;

    @Inject
    public Population(PositionCalculator positionCalculator) {
        this.positionCalculator = positionCalculator;
        currentPopulation = Maps.newHashMap();
    }

    public void addCell(Cell cell) {
        currentPopulation.put(cell.getPosition(), cell);
    }

    public ImmutableSet<Cell> getMembers() {
        return ImmutableSet.copyOf(currentPopulation.values());
    }

    @Override
    public boolean isAlive(Position position) {
        return currentPopulation.containsKey(position);
    }

    @Override
    public int getNumberOfLivingNeighbours(Position position) {
        final Predicate<Position> isAlive = new Predicate<Position>() {

            @Override
            public boolean apply(Position position) {
                return isAlive(position);
            }
        };
        Set<Position> neighboursPositions = positionCalculator.getNeighboursPositions(position);
        return Sets.filter(neighboursPositions, isAlive).size();
    }

    public void applyPopulationChanges(PopulationChange populationChange) {
        removeCells(populationChange.getShouldBeKilled());
        addCells(populationChange.getShouldBeBorne());
    }

    private void addCells(Iterable<Cell> cells) {
        for (Cell cellToBeBorn : cells) {
            addCell(cellToBeBorn);
        }
    }

    private void removeCells(Iterable<Cell> cells) {
        for (Cell cellToBeKilled : cells) {
            removeCell(cellToBeKilled);
        }
    }

    private void removeCell(Cell cellToBeKilled) {
        currentPopulation.remove(cellToBeKilled.getPosition());
    }
}
