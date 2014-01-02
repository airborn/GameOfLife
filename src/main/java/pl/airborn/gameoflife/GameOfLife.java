package pl.airborn.gameoflife;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import pl.airborn.gameoflife.configuration.Configuration;
import pl.airborn.gameoflife.inject.ConfiguredModule;
import pl.airborn.gameoflife.position.Position;

import java.util.Collection;

public class GameOfLife {
    private final Injector injector;

    public GameOfLife(Configuration configuration) {
        Module configuredModule = new ConfiguredModule(configuration);
        injector = Guice.createInjector(configuredModule);
    }

    public World createWorld(Collection<Position> positions) {
        World world = injector.getInstance(World.class);

        for (Position position : positions) {
            world.addCellAt(position);
        }

        return world;
    }
}
