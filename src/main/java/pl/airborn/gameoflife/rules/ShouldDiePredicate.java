package pl.airborn.gameoflife.rules;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.PopulationStateChecker;
import pl.airborn.gameoflife.inject.NeighboursRequiredToSurvive;
import pl.airborn.gameoflife.position.Position;

@Singleton
public class ShouldDiePredicate implements Predicate<Position> {
    private final PopulationStateChecker populationStateChecker;
    private final ImmutableSet<Integer> neighboursRequiredToSurvive;

    @Inject
    public ShouldDiePredicate(PopulationStateChecker populationStateChecker, @NeighboursRequiredToSurvive ImmutableSet<Integer> neighboursRequiredToSurvive) {
        this.populationStateChecker = populationStateChecker;
        this.neighboursRequiredToSurvive = neighboursRequiredToSurvive;
    }

    @Override
    public boolean apply(Position position) {
        int livingNeighbours = populationStateChecker.getNumberOfLivingNeighbours(position);
        return !neighboursRequiredToSurvive.contains(livingNeighbours);
    }
}
