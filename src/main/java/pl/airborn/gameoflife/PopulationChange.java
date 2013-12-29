package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;

public class PopulationChange {

    private final ImmutableSet<Position> shouldBeBorne;
    private final ImmutableSet<Position> shouldBeKilled;

    public PopulationChange(ImmutableSet<Position> shouldBeBorn, ImmutableSet<Position> shouldBeKill) {
        this.shouldBeBorne = shouldBeBorn;
        this.shouldBeKilled = shouldBeKill;
    }

    public ImmutableSet<Position> getShouldBeBorne() {
        return shouldBeBorne;
    }

    public ImmutableSet<Position> getShouldBeKilled() {
        return shouldBeKilled;
    }
}
