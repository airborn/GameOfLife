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
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.rules.ShouldBornPredicate;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class ShouldBornPredicateTest {

    @InjectMocks
    private ShouldBornPredicate shouldBornPredicate;
    @Mock
    private Population population;
    @Mock
    private Position position;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
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
        when(population.getNumberOfLivingNeighbours(position)).thenReturn(neighbours);

        // when
        boolean actual = shouldBornPredicate.apply(position);

        // then
        assertThat(actual).isFalse();
    }

    @Test
    public void shouldBorn() throws Exception {
        // given
        when(population.getNumberOfLivingNeighbours(position)).thenReturn(3);

        // when
        boolean actual = shouldBornPredicate.apply(position);

        // then
        assertThat(actual).isTrue();
    }
}
