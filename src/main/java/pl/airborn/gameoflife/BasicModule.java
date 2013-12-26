package pl.airborn.gameoflife;

import com.google.inject.AbstractModule;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PopulationStateChecker.class).to(Population.class);
    }
}
