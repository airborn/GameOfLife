package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.airborn.gameoflife.inject.BasicModule;
import pl.airborn.gameoflife.mapping.CellMapper;

import java.util.Collection;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class GameOfLifeIT {

    @Test
    @FileParameters(mapper = CellMapper.class, value = "classpath:simple.json")
    public void shouldEvolveToGivenState(Cell[] before, Map<Integer, Cell[]> evolutionsSteps) throws Exception {
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

    private World createWorld(Cell[] cells) {
        Injector injector = Guice.createInjector(new BasicModule());
        World world = injector.getInstance(World.class);

        for (Cell cell : cells) {
            world.addCellAt(cell.getPosition());
        }

        return world;
    }
}
