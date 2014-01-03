package pl.airborn.gameoflife;

import org.junit.Test;
import pl.airborn.gameoflife.position.Position;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CellTest {

    @Test
    public void shouldReturnPosition() {
        //given
        Position expected = mock(Position.class);
        Cell cell = new Cell(expected);

        //when
        Position actual = cell.getPosition();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
