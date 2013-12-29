package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Map;

@Singleton
public class Population {


    private final Map<Position, Cell> currentPopulation = Maps.newHashMap();
    private final CellFactory cellFactory;

    @Inject
    public Population(CellFactory cellFactory) {
        this.cellFactory = cellFactory;
    }

    public void createCellAt(Position position) {
        Cell cell = cellFactory.createCell(position);
        currentPopulation.put(position, cell);
    }

    public ImmutableSet<Cell> getMembers() {
        return ImmutableSet.copyOf(currentPopulation.values());
    }

    public ImmutableSet<Position> getMembersPositions() {
        return ImmutableSet.copyOf(currentPopulation.keySet());
    }

    public boolean isAlive(Position position) {
        return currentPopulation.containsKey(position);
    }

    public void applyPopulationChanges(PopulationChange populationChange) {
        removeCells(populationChange.getShouldBeKilled());
        addCells(populationChange.getShouldBeBorne());
    }

    private void addCells(Iterable<Position> positions) {
        for (Position position : positions) {
            createCellAt(position);
        }
    }

    private void removeCells(Iterable<Position> positions) {
        for (Position position : positions) {
            removeCell(position);
        }
    }

    private void removeCell(Position positionToBeKilled) {
        currentPopulation.remove(positionToBeKilled);
    }
}
