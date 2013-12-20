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


    @Test
    public void shouldReturnNeighboursPositions() throws Exception {
        // given
        Position position = mock(Position.class);
        Set<Position> expected = mock(Set.class);
        when(position.getNeighboursPositions()).thenReturn(expected);
        Cell cell = new Cell(position);

        // when
        Set<Position> actual = cell.getNeighboursPositions();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
