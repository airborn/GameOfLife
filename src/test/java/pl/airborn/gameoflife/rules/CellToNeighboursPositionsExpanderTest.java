package pl.airborn.gameoflife.rules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.position.PositionCalculator;
import pl.airborn.gameoflife.rules.CellToNeighboursPositionsExpander;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CellToNeighboursPositionsExpanderTest {

    @InjectMocks
    private CellToNeighboursPositionsExpander expander;
    @Mock
    private PositionCalculator positionCalculator;

    @Test
    public void shouldGetNeighboursPosition() throws Exception {
        // given
        Cell cell = mock(Cell.class);
        Position position = mock(Position.class);
        when(cell.getPosition()).thenReturn(position);
        Set<Position> expected = mock(Set.class);
        when(positionCalculator.getNeighboursPositions(position)).thenReturn(expected);

        // when
        Iterable<Position> actual = expander.apply(cell);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
