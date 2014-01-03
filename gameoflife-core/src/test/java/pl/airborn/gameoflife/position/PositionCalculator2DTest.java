package pl.airborn.gameoflife.position;

import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class PositionCalculator2DTest {

    private PositionCalculator2D positionCalculator = new PositionCalculator2D();

    @Test
    public void shouldReturnNeighboursPositions() throws Exception {
        // given
        Position[] expectedNeighbours = new Position[]{
                new Position2D(2, 3), new Position2D(3, 3), new Position2D(4, 3),
                new Position2D(2, 4), new Position2D(4, 4),
                new Position2D(2, 5), new Position2D(3, 5), new Position2D(4, 5)
        };

        Position2D position = new Position2D(3, 4);

        // when
        Set<Position> actual = positionCalculator.getNeighboursPositions(position);

        // then
        assertThat(actual).containsOnly(expectedNeighbours);
    }
}
