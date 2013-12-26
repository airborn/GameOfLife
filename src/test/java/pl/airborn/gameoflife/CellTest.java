package pl.airborn.gameoflife;

import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CellTest {

    @Test
    public void shouldReturnPosition() {
        //given
        Position expected = new Position(3, 4);
        Cell cell = new Cell(expected);

        //when
        Position actual = cell.getPosition();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
