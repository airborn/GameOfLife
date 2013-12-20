package pl.airborn.gameoflife.rules;

import org.junit.Test;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.rules.CellToNeighboursPositionsExpander;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CellToNeighboursPositionsExpanderTest {

    @Test
    public void shouldGetNeighboursPosition() throws Exception {
        // given
        CellToNeighboursPositionsExpander expander = new CellToNeighboursPositionsExpander();
        Cell cell = mock(Cell.class);
        Set<Position> expected = mock(Set.class);
        when(cell.getNeighboursPositions()).thenReturn(expected);

        // when
        Iterable<Position> actual = expander.apply(cell);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
