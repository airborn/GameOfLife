package pl.airborn.gameoflife.rules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.rules.KillRules;

import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KillRulesTest {

    @InjectMocks
    private KillRules killRules;
    @Mock
    private ShouldDiePredicate shouldDiePredicate;

    @Test
    public void shouldFindSurvivors() throws Exception {
        // given
        when(shouldDiePredicate.apply(new Cell(new Position(2, 2)))).thenReturn(true);
        when(shouldDiePredicate.apply(new Cell(new Position(2, 4)))).thenReturn(true);

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
