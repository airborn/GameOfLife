package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.airborn.gameoflife.configuration.Configuration;
import pl.airborn.gameoflife.configuration.ConwayConfiguration;
import pl.airborn.gameoflife.mapping.CellMapper;
import pl.airborn.gameoflife.position.Position;
import pl.airborn.gameoflife.position.Position2D;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class GameOfLifeIT {

    @Test
    @FileParameters(mapper = CellMapper.class, value = "classpath:simple.json")
    public void shouldEvolveToGivenState(Position2D[] before, Map<Integer, Cell[]> evolutionsSteps) throws Exception {
        World world = createWorld(before);
        Integer maxAge = Ordering.natural().max(evolutionsSteps.keySet());

        for (int age = 0; age < maxAge; age++) {
            if (evolutionsSteps.containsKey(age)) {
                Cell[] expected = evolutionsSteps.get(age);
                ImmutableSet<Cell> actual = world.getPopulationMembers();
                assertPopulationState(actual, expected);
            }
            world.evolve();
        }
    }

    private void assertPopulationState(Collection<Cell> actual, Cell[] expected) {
        if (expected.length > 0) {
            assertThat(actual).containsOnly(expected);
        } else {
            assertThat(actual).isEmpty();
        }
    }

    private World createWorld(Position2D[] positions) {
        Configuration configuration = new ConwayConfiguration();
        GameOfLife game = new GameOfLife(configuration);
        List<Position> positionsList = Arrays.<Position>asList(positions);
        World world = game.createWorld(positionsList);
        return world;
    }
}
