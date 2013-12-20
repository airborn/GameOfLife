package pl.airborn.gameoflife;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Set;

public class PopulationChange {

    private final Set<Cell> shouldBeBorne;
    private final Set<Cell> shouldBeKilled;

    public PopulationChange(Collection<Cell> shouldBeBorn, Collection<Cell> shouldBeKill) {
        this.shouldBeBorne = Sets.newHashSet(shouldBeBorn);
        this.shouldBeKilled = Sets.newHashSet(shouldBeKill);
    }

    public Set<Cell> getShouldBeBorne() {
        return shouldBeBorne;
    }

    public Set<Cell> getShouldBeKilled() {
        return shouldBeKilled;
    }
}
