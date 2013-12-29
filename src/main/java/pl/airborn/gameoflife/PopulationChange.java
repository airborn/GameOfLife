package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;

public class PopulationChange {

    private final ImmutableSet<Cell> shouldBeBorne;
    private final ImmutableSet<Cell> shouldBeKilled;

    public PopulationChange(ImmutableSet<Cell> shouldBeBorn, ImmutableSet<Cell> shouldBeKill) {
        this.shouldBeBorne = shouldBeBorn;
        this.shouldBeKilled = shouldBeKill;
    }

    public ImmutableSet<Cell> getShouldBeBorne() {
        return shouldBeBorne;
    }

    public ImmutableSet<Cell> getShouldBeKilled() {
        return shouldBeKilled;
    }
}
