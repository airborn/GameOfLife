package pl.airborn.gameoflife.position;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class Position2DTest {

    @Test
    public void shouldComparePositions_same() throws Exception {
        // given
        Position position1 = new Position2D(4, 5);
        Position position2 = new Position2D(4, 5);

        // then
        assertThat(position1).isEqualTo(position2);
    }

    @Test
    public void shouldComparePositions_different() throws Exception {
        // given
        Position position1 = new Position2D(4, 5);
        Position position2 = new Position2D(5, 4);

        // then
        assertThat(position1).isNotEqualTo(position2);
    }
}
