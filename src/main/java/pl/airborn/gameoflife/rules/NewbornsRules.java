package pl.airborn.gameoflife.rules;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;

import java.util.Set;

@Singleton
public class NewbornsRules {

    private final CellFactory cellFactory;
    private final CellToNeighboursPositionsExpander cellToNeighboursPositionsExpander;
    private final ShouldBornPredicate shouldBorn;

    @Inject
    public NewbornsRules(CellFactory cellFactory, CellToNeighboursPositionsExpander cellToNeighboursPositionsExpander, ShouldBornPredicate shouldBorn) {
        this.cellFactory = cellFactory;
        this.cellToNeighboursPositionsExpander = cellToNeighboursPositionsExpander;
        this.shouldBorn = shouldBorn;
    }

    public ImmutableSet<Cell> getNewborns(final Population currentPopulation) {
        Set<Cell> populationMembers = currentPopulation.getMembers();
        ImmutableSet<Cell> newborns = FluentIterable.from(populationMembers)
                .transformAndConcat(cellToNeighboursPositionsExpander)
                .filter(shouldBorn)
                .transform(cellFactory)
                .toSet();
        return ImmutableSet.copyOf(newborns);
    }
}
