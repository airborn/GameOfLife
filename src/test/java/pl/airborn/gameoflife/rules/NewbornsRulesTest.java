package pl.airborn.gameoflife.rules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.rules.NewbornsRules;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewbornsRulesTest {

    @InjectMocks
    private NewbornsRules newbornsRules;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private CellFactory cellFactory;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private CellToNeighboursPositionsExpander cellToNeighboursPositionsExpander;
    @Mock
    private ShouldBornPredicate shouldBorn;

    @Test
    public void shouldFindNewborns() throws Exception {
        // given
        when(shouldBorn.apply(new Position(1, 3))).thenReturn(true);
        when(shouldBorn.apply(new Position(3, 3))).thenReturn(true);

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
