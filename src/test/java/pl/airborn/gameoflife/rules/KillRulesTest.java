package pl.airborn.gameoflife.rules;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.position.Position;

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
        Position expected1 = mock(Position.class);
        Position expected2 = mock(Position.class);
        Position positionToDie = mock(Position.class);
        when(shouldDiePredicate.apply(expected1)).thenReturn(true);
        when(shouldDiePredicate.apply(expected2)).thenReturn(true);

        ImmutableSet<Position> populationMembers = ImmutableSet.of(
                expected1,
                positionToDie,
                expected2);
        when(population.getMembersPositions()).thenReturn(populationMembers);

        Position[] expected = new Position[]{
                expected1,
                expected2
        };

        // when
        ImmutableSet<Position> actual = killRules.getKilled(population);

        // then
        assertThat(actual).containsOnly(expected);
    }
}
