package pl.airborn.gameoflife.rules;

import org.junit.Test;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.rules.KillRules;

import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;

public class KillRulesTest {

    private KillRules killRules = new KillRules();

    @Test
    public void shouldFindSurvivors() throws Exception {
        // given
        Population population = new Population();
        population.addCell(new Cell(new Position(2, 2)));
        population.addCell(new Cell(new Position(2, 3)));
        population.addCell(new Cell(new Position(2, 4)));
        Cell[] expected = new Cell[]{
                new Cell(new Position(2, 2)),
                new Cell(new Position(2, 4))
        };

        // when
        Collection<Cell> actual = killRules.getKilled(population);

        // then
        assertThat(actual).containsOnly(expected);
    }
}
