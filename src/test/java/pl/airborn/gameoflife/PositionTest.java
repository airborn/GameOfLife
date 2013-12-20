package pl.airborn.gameoflife;

import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class PositionTest {

    @Test
    public void shouldComparePositions_same() throws Exception {
        // given
        Position position1 = new Position(4, 5);
        Position position2 = new Position(4, 5);

        // then
        assertThat(position1).isEqualTo(position2);
    }

    @Test
    public void shouldComparePositions_different() throws Exception {
        // given
        Position position1 = new Position(4, 5);
        Position position2 = new Position(5, 4);

        // then
        assertThat(position1).isNotEqualTo(position2);
    }

    @Test
    public void shouldReturnNeighboursPositions() throws Exception {
        // given
        Position[] expectedNeighbours = new Position[]{
                new Position(2, 3), new Position(3, 3), new Position(4, 3),
                new Position(2, 4), new Position(4, 4),
                new Position(2, 5), new Position(3, 5), new Position(4, 5)
        };

        Position position = new Position(3, 4);

        // when
        Set<Position> actual = position.getNeighboursPositions();

        // then
        assertThat(actual).containsOnly(expectedNeighbours);
    }
}
