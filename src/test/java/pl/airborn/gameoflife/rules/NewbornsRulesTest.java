package pl.airborn.gameoflife.rules;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.airborn.gameoflife.Population;
import pl.airborn.gameoflife.position.Position;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewbornsRulesTest {

    @InjectMocks
    private NewbornsRules newbornsRules;
    @Mock
    private NeighboursExpander neighboursExpander;
    @Mock
    private ShouldBornPredicate shouldBorn;
    @Mock
    private Population population;

    @Test
    public void shouldFindNewborns() throws Exception {
        // given
        Position position = mock(Position.class);
        ImmutableSet<Position> populationMembers = ImmutableSet.of(position);
        when(population.getMembersPositions()).thenReturn(populationMembers);

        Position expectedPosition = mock(Position.class);
        Position unexpectedPosition = mock(Position.class);
        Iterable<Position> neighbours = Lists.newArrayList(expectedPosition, unexpectedPosition );
        when(neighboursExpander.apply(position)).thenReturn(neighbours);

        when(shouldBorn.apply(expectedPosition)).thenReturn(true);

        Position[] expected = new Position[]{expectedPosition};

        // when
        Set<Position> actual = newbornsRules.getNewborns(population);

        // then
        assertThat(actual).containsOnly(expected);
    }
}
