package pl.airborn.gameoflife.rules;

import com.google.common.collect.FluentIterable;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.rules.CellFactory;
import pl.airborn.gameoflife.rules.CellToNeighboursPositionsExpander;
import pl.airborn.gameoflife.rules.ShouldBornPredicate;

import java.util.Collection;
import java.util.Set;

public class NewbornsRules {

    private final CellFactory cellFactory = new CellFactory();
    private final CellToNeighboursPositionsExpander cellToNeighboursPositionsExpander = new CellToNeighboursPositionsExpander();

    public Set<Cell> getNewborns(final Population currentPopulation) {
        final ShouldBornPredicate shouldBorn = new ShouldBornPredicate(currentPopulation);
        Collection<Cell> populationMembers = currentPopulation.getMembers();
        return FluentIterable.from(populationMembers).transformAndConcat(cellToNeighboursPositionsExpander).filter(shouldBorn).transform(cellFactory).toSet();
    }
}
