package pl.airborn.gameoflife.configuration;

import com.google.common.collect.ImmutableSet;
import pl.airborn.gameoflife.position.PositionCalculator;

public interface Configuration {
    Class<? extends PositionCalculator> getPositionCalculator();

    ImmutableSet<Integer> getNeighboursRequiredToBorn();

    ImmutableSet<Integer> getNeighboursRequiredToSurvive();
}

