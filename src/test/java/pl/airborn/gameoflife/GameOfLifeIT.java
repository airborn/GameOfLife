package pl.airborn.gameoflife;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.airborn.gameoflife.mapping.CellMapper;
import pl.airborn.gameoflife.rules.KillRules;
import pl.airborn.gameoflife.rules.NewbornsRules;

import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class GameOfLifeIT {

    @Test
    @FileParameters(mapper = CellMapper.class, value = "classpath:file.json")
    public void shouldEvolveToGivenState(Cell[] before, Cell[] expected, int evolutions) throws Exception {
        // given
        World world = createWorld(before);

        // when
        for (int i = 0; i < evolutions; i++) {
            world.evolve();
        }

        // then
        Collection<Cell> populationMembers = world.getPopulationMembers();
        if (expected.length > 0) {
            assertThat(populationMembers).containsOnly(expected);
        } else {
            assertThat(populationMembers).isEmpty();
        }
    }

    private World createWorld(Cell... cells) {
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
