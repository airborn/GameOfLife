package pl.airborn.gameoflife.rules;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.rules.NewbornsRules;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewbornsRulesTest {

    @InjectMocks
    private NewbornsRules newbornsRules;
    @Mock
    private CellFactory cellFactory;
    @Mock
    private CellToNeighboursPositionsExpander cellToNeighboursPositionsExpander;
    @Mock
    private ShouldBornPredicate shouldBorn;
    @Mock
    private Population population;

    @Test
    public void shouldFindNewborns() throws Exception {
        // given
        Cell cell = mock(Cell.class);
        ImmutableSet<Cell> populationMembers = ImmutableSet.of(cell);
        when(population.getMembers()).thenReturn(populationMembers);

        Position expectedPosition = mock(Position.class);
        Position unexpectedPosition = mock(Position.class);
        Iterable<Position> neighbours = Lists.newArrayList(expectedPosition, unexpectedPosition );
        when(cellToNeighboursPositionsExpander.apply(cell)).thenReturn(neighbours);

        Cell expectedCell = new Cell(expectedPosition);
        when(shouldBorn.apply(expectedPosition)).thenReturn(true);

        when(cellFactory.apply(expectedPosition)).thenReturn(expectedCell);

        Cell[] expected = new Cell[]{expectedCell};

        // when
        Set<Cell> actual = newbornsRules.getNewborns(population);

        // then
        verify(cellFactory, never()).apply(unexpectedPosition);
        assertThat(actual).containsOnly(expected);
    }
}
