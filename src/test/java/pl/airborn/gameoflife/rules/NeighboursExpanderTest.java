package pl.airborn.gameoflife.rules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.airborn.gameoflife.position.Position;
import pl.airborn.gameoflife.position.PositionCalculator;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NeighboursExpanderTest {

    @InjectMocks
    private NeighboursExpander expander;
    @Mock
    private PositionCalculator positionCalculator;

    @Test
    public void shouldGetNeighboursPosition() throws Exception {
        // given
        Position position = mock(Position.class);
        Set<Position> expected = mock(Set.class);
        when(positionCalculator.getNeighboursPositions(position)).thenReturn(expected);

        // when
        Iterable<Position> actual = expander.apply(position);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
