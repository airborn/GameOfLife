package pl.airborn.gameoflife.rules;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.airborn.gameoflife.Cell;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.Position;
import pl.airborn.gameoflife.rules.KillRules;

import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KillRulesTest {

    @InjectMocks
    private KillRules killRules;
    @Mock
    private ShouldDiePredicate shouldDiePredicate;
    @Mock
    private Population population;

    @Test
    public void shouldFindSurvivors() throws Exception {
        // given
        Cell expected1 = mock(Cell.class);
        Cell expected2 = mock(Cell.class);
        Cell cellToDie = mock(Cell.class);
        when(shouldDiePredicate.apply(expected1)).thenReturn(true);
        when(shouldDiePredicate.apply(expected2)).thenReturn(true);

        ImmutableSet<Cell> populationMembers = ImmutableSet.of(
                expected1,
                cellToDie,
                expected2);
        when(population.getMembers()).thenReturn(populationMembers);

        Cell[] expected = new Cell[]{
                expected1,
                expected2
        };

        // when
        Collection<Cell> actual = killRules.getKilled(population);

        // then
        assertThat(actual).containsOnly(expected);
    }
}
