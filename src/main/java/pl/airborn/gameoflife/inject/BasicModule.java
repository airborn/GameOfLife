package pl.airborn.gameoflife.inject;

import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import pl.airborn.gameoflife.inject.NeighboursRequiredToBorn;
import pl.airborn.gameoflife.inject.NeighboursRequiredToSurvive;

import java.util.Set;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<Set<Integer>>() {
        }).annotatedWith(NeighboursRequiredToBorn.class).toInstance(ImmutableSet.of(3));
        bind(new TypeLiteral<Set<Integer>>() {
        }).annotatedWith(NeighboursRequiredToSurvive.class).toInstance(ImmutableSet.of(2, 3));
    }
}
