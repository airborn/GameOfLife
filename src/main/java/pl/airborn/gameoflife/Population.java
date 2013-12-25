package pl.airborn.gameoflife;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Population implements PopulationStateChecker {
    private final Map<Position, Cell> currentPopulation = Maps.newHashMap();

    public void addCell(Cell cell) {
        currentPopulation.put(cell.getPosition(), cell);
    }

    public Collection<Cell> getMembers() {
        return currentPopulation.values();
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
        Set<Position> neighboursPositions = position.getNeighboursPositions();
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
