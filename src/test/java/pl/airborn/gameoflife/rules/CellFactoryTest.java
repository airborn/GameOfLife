package pl.airborn.gameoflife.rules;

import org.junit.Test;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.rules.CellFactory;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CellFactoryTest {

    private CellFactory cellFactory = new CellFactory();

    @Test
    public void shouldCreateCell() throws Exception {
        // given
        Position expected = mock(Position.class);

        // when
        Cell cell = cellFactory.apply(expected);

        // then
        assertThat(cell.getPosition()).isEqualTo(expected);
    }
}
