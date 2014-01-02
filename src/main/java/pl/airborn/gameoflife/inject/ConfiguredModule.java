package pl.airborn.gameoflife.inject;

import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import pl.airborn.gameoflife.configuration.Configuration;
import pl.airborn.gameoflife.position.PositionCalculator;

public class ConfiguredModule extends AbstractModule {

    private final Configuration configuration;

    public ConfiguredModule(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(PositionCalculator.class).to(configuration.getPositionCalculator());
        bind(new TypeLiteral<ImmutableSet<Integer>>() {
        }).annotatedWith(NeighboursRequiredToBorn.class).toInstance(configuration.getNeighboursRequiredToBorn());
        bind(new TypeLiteral<ImmutableSet<Integer>>() {
        }).annotatedWith(NeighboursRequiredToSurvive.class).toInstance(configuration.getNeighboursRequiredToSurvive());
    }
}
