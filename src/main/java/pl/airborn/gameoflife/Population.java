package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.inject.Singleton;

import java.util.Map;

@Singleton
public class Population {

    private final Map<Position, Cell> currentPopulation = Maps.newHashMap();

    public void addCell(Cell cell) {
        currentPopulation.put(cell.getPosition(), cell);
    }

    public ImmutableSet<Cell> getMembers() {
        return ImmutableSet.copyOf(currentPopulation.values());
    }

    public boolean isAlive(Position position) {
        return currentPopulation.containsKey(position);
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
