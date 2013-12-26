package pl.airborn.gameoflife;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Collection;

@Singleton
public class World {

    private final GameRules gameRules;
    private final Population population;

    @Inject
    public World(GameRules gameRules, Population population) {
        this.gameRules = gameRules;
        this.population = population;
    }

    public Collection<Cell> getPopulationMembers() {
        return population.getMembers();
    }

    public void evolve() {
        PopulationChange populationChange = gameRules.apply(population);
        population.applyPopulationChanges(populationChange);
    }

    public void addCell(Cell cell) {
        population.addCell(cell);
    }
}
