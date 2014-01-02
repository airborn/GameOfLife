package pl.airborn.gameoflife;

import org.junit.Test;
import pl.airborn.gameoflife.position.Position;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CellFactoryTest {

    private CellFactory cellFactory = new CellFactory();

    @Test
    public void shouldCreateCell() throws Exception {
        // given
        Position expected = mock(Position.class);

        // when
        Cell cell = cellFactory.createCell(expected);

        // then
        assertThat(cell.getPosition()).isEqualTo(expected);
    }
}
