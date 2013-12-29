package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorldTest {

    @InjectMocks
    private World world;
    @Mock
    private GameRules gameRules;
    @Mock
    private Population population;

    @Test
    public void shouldReturnPopulationMembers() throws Exception {
        // given
        ImmutableSet<Cell> expected = mock(ImmutableSet.class);
        when(population.getMembers()).thenReturn(expected);

        // when
        ImmutableSet<Cell> actual = world.getPopulationMembers();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldApplyPopulationChanges() throws Exception {
        // given
        PopulationChange populationChange = mock(PopulationChange.class);
        when(gameRules.apply(population)).thenReturn(populationChange);

        // when
        world.evolve();

        // then
        verify(gameRules).apply(population);
        verify(population).applyPopulationChanges(populationChange);
    }
}
