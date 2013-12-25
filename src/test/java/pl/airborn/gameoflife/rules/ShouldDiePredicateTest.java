package pl.airborn.gameoflife.rules;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.PopulationStateChecker;
import pl.airborn.gameoflife.Position;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class ShouldDiePredicateTest {

    @InjectMocks
    private ShouldDiePredicate shouldDiePredicate;
    @Mock
    private PopulationStateChecker populationStateChecker;
    @Mock
    private Cell cell;
    @Mock
    private Position position;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(cell.getPosition()).thenReturn(position);
    }

    @Test
    @Parameters({"2", "3"})
    public void shouldSurvive(int neighbours) throws Exception {
        // given
        when(populationStateChecker.getNumberOfLivingNeighbours(position)).thenReturn(neighbours);

        // when
        boolean actual = shouldDiePredicate.apply(cell);

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @Parameters({"0", "1", "4", "5", "6", "7", "8"})
    public void shouldDie(int neighbours) throws Exception {
        // given
        when(populationStateChecker.getNumberOfLivingNeighbours(position)).thenReturn(neighbours);

        // when
        boolean actual = shouldDiePredicate.apply(cell);

        // then
        assertThat(actual).isTrue();
    }
}
