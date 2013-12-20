package pl.airborn.gameoflife.rules;

import org.junit.Test;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.rules.NewbornsRules;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class NewbornsRulesTest {

    private NewbornsRules newbornsRules = new NewbornsRules();

    @Test
    public void shouldFindNewborns() throws Exception {
        // given
        Population population = new Population();
        population.addCell(new Cell(new Position(2, 2)));
        population.addCell(new Cell(new Position(2, 3)));
        population.addCell(new Cell(new Position(2, 4)));
        Cell[] expected = new Cell[]{
                new Cell(new Position(1, 3)),
                new Cell(new Position(3, 3))
        };

        // when
        Set<Cell> actual = newbornsRules.getNewborns(population);

        // then
        assertThat(actual).containsOnly(expected);
    }
}
