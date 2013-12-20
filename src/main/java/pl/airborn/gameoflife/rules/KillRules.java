package pl.airborn.gameoflife.rules;

import com.google.common.collect.Collections2;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;

import java.util.Collection;

public class KillRules {

    public Collection<Cell> getKilled(Population currentPopulation) {
        final ShouldDiePredicate shouldDie = new ShouldDiePredicate(currentPopulation);
        Collection<Cell> populationMembers = currentPopulation.getMembers();
        return Collections2.filter(populationMembers, shouldDie);
    }
}
