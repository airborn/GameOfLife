package pl.airborn.gameoflife;

import com.google.common.collect.Ordering;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.airborn.gameoflife.mapping.CellMapper;
import pl.airborn.gameoflife.rules.KillRules;
import pl.airborn.gameoflife.rules.NewbornsRules;

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
                Collection<Cell> actual = world.getPopulationMembers();
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
        Population population = new Population();
        for (Cell cell : cells) {
            population.addCell(cell);
        }
        KillRules killRules = new KillRules();
        NewbornsRules newbornsRules = new NewbornsRules();
        GameRules gameRules = new GameRules(killRules, newbornsRules);
        return new World(gameRules, population);
    }
}
