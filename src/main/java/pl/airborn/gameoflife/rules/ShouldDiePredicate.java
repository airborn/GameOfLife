package pl.airborn.gameoflife.rules;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.PopulationStateChecker;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.inject.NeighboursRequiredToSurvive;

import java.util.Set;

@Singleton
public class ShouldDiePredicate implements Predicate<Position> {
    private final PopulationStateChecker populationStateChecker;
    private final Set<Integer> neighboursRequiredToSurvive;

    @Inject
    public ShouldDiePredicate(PopulationStateChecker populationStateChecker, @NeighboursRequiredToSurvive Set<Integer> neighboursRequiredToSurvive) {
        this.populationStateChecker = populationStateChecker;
        this.neighboursRequiredToSurvive = neighboursRequiredToSurvive;
    }

    @Override
    public boolean apply(Position position) {
        int livingNeighbours = populationStateChecker.getNumberOfLivingNeighbours(position);
        return !neighboursRequiredToSurvive.contains(livingNeighbours);
    }
}
