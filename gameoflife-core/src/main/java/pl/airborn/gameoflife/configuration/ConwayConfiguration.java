package pl.airborn.gameoflife.configuration;

import com.google.common.collect.ImmutableSet;
import pl.airborn.gameoflife.position.PositionCalculator;
import pl.airborn.gameoflife.position.PositionCalculator2D;

public class ConwayConfiguration implements Configuration {
    @Override
    public Class<? extends PositionCalculator> getPositionCalculator() {
        return PositionCalculator2D.class;
    }

    @Override
    public ImmutableSet<Integer> getNeighboursRequiredToBorn() {
        return ImmutableSet.of(3);
    }

    @Override
    public ImmutableSet<Integer> getNeighboursRequiredToSurvive() {
        return ImmutableSet.of(2, 3);
    }
}
