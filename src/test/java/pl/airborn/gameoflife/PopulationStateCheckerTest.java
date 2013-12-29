package pl.airborn.gameoflife;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.airborn.gameoflife.position.PositionCalculator;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PopulationStateCheckerTest {

    @InjectMocks
    private PopulationStateChecker populationStateChecker;
    @Mock
    private PositionCalculator positionCalculator;
    @Mock
    private Population population;

    @Test
    public void shouldReturnNumberOfLivingNeighbours() throws Exception {
        // given
        final int expected = 2;

        Position cellPosition = mock(Position.class);
        
        Position neighbour1 = mock(Position.class);
        Position neighbour2 = mock(Position.class);
        Position neighbour3 = mock(Position.class);
        when(positionCalculator.getNeighboursPositions(cellPosition)).thenReturn(
                Sets.newHashSet(neighbour1, neighbour2, neighbour3));

        when(population.isAlive(neighbour2)).thenReturn(true);
        when(population.isAlive(neighbour3)).thenReturn(true);

        // when
        int actual = populationStateChecker.getNumberOfLivingNeighbours(cellPosition);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
