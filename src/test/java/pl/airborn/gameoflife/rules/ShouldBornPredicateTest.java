package pl.airborn.gameoflife.rules;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.PopulationStateChecker;
import pl.airborn.gameoflife.Position;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class ShouldBornPredicateTest {

    public static final int NEIGHBOURS_REQUIRED_TO_BORN = 3;
    @InjectMocks
    private ShouldBornPredicate shouldBornPredicate;
    @Mock
    private PopulationStateChecker populationStateChecker;
    @Mock
    private Position position;
    @Mock
    private Set<Integer> neighboursRequiredToBorn;
    @Mock
    private Population population;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(neighboursRequiredToBorn.contains(NEIGHBOURS_REQUIRED_TO_BORN)).thenReturn(true);
    }

    @Test
    public void shouldNotReborn_LivingCell() throws Exception {
        // given
        when(population.isAlive(position)).thenReturn(true);

        // when
        boolean actual = shouldBornPredicate.apply(position);

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @Parameters({"0", "1", "2", "4", "5", "6", "7", "8"})
    public void shouldNotBorn(int neighbours) throws Exception {
        // given
        when(populationStateChecker.getNumberOfLivingNeighbours(position)).thenReturn(neighbours);

        // when
        boolean actual = shouldBornPredicate.apply(position);

        // then
        assertThat(actual).isFalse();
    }

    @Test
    public void shouldBorn() throws Exception {
        // given
        when(populationStateChecker.getNumberOfLivingNeighbours(position)).thenReturn(NEIGHBOURS_REQUIRED_TO_BORN);

        // when
        boolean actual = shouldBornPredicate.apply(position);

        // then
        assertThat(actual).isTrue();
    }
}
