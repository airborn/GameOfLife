package pl.airborn.gameoflife;

import java.util.Collection;

public class World {

    private final GameRules gameRules;
    private final Population population;

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
}
