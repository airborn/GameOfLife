package pl.airborn.gameoflife.rules;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.position.Position;

import java.util.Set;

@Singleton
public class NewbornsRules {

    private final NeighboursExpander neighboursExpander;
    private final ShouldBornPredicate shouldBorn;

    @Inject
    public NewbornsRules(NeighboursExpander neighboursExpander, ShouldBornPredicate shouldBorn) {
        this.neighboursExpander = neighboursExpander;
        this.shouldBorn = shouldBorn;
    }

    public ImmutableSet<Position> getNewborns(final Population currentPopulation) {
        Set<Position> populationMembersPositions = currentPopulation.getMembersPositions();
        return FluentIterable.from(populationMembersPositions)
                .transformAndConcat(neighboursExpander)
                .filter(shouldBorn)
                .toSet();
    }
}
