package pl.airborn.gameoflife.position;

import org.junit.Test;
import pl.airborn.gameoflife.Position;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class PositionCalculatorTest {

    PositionCalculator positionCalculator = new PositionCalculator();

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
        Set<Position> actual = positionCalculator.getNeighboursPositions(position);

        // then
        assertThat(actual).containsOnly(expectedNeighbours);
    }
}
