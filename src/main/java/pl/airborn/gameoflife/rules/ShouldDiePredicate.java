package pl.airborn.gameoflife.rules;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.PopulationStateChecker;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.inject.NeighboursRequiredToSurvive;

import java.util.Set;

@Singleton
public class ShouldDiePredicate implements Predicate<Cell> {
    private final PopulationStateChecker populationStateChecker;
    private final Set<Integer> neighboursRequiredToSurvive;

    @Inject
    public ShouldDiePredicate(PopulationStateChecker populationStateChecker, @NeighboursRequiredToSurvive Set<Integer> neighboursRequiredToSurvive) {
        this.populationStateChecker = populationStateChecker;
        this.neighboursRequiredToSurvive = neighboursRequiredToSurvive;
    }

    @Override
    public boolean apply(Cell cell) {
        Position cellPosition = cell.getPosition();
        int livingNeighbours = populationStateChecker.getNumberOfLivingNeighbours(cellPosition);
        return !neighboursRequiredToSurvive.contains(livingNeighbours);
    }
}
