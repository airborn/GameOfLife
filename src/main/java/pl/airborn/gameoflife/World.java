package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class World {

    private final GameRules gameRules;
    private final Population population;

    @Inject
    public World(GameRules gameRules, Population population) {
        this.gameRules = gameRules;
        this.population = population;
    }

    public ImmutableSet<Cell> getPopulationMembers() {
        return population.getMembers();
    }

    public void evolve() {
        PopulationChange populationChange = gameRules.apply(population);
        population.applyPopulationChanges(populationChange);
    }

    public void addCellAt(Position position) {
        population.createCellAt(position);
    }
}
