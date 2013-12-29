package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PopulationChangeTest {

    @Test
    public void shouldReturnPopulationChanges() throws Exception {
        // given
        Position positionsToBorn = mock(Position.class);
        ImmutableSet<Position> shouldBeBorn = ImmutableSet.of(positionsToBorn);
        Position positionsToBeKilled = mock(Position.class);
        ImmutableSet<Position> shouldBeKill = ImmutableSet.of(positionsToBeKilled);

        // when
        PopulationChange populationChange = new PopulationChange(shouldBeBorn, shouldBeKill);

        // then
        assertThat(populationChange.getShouldBeBorne()).containsOnly(positionsToBorn);
        assertThat(populationChange.getShouldBeKilled()).containsOnly(positionsToBeKilled);
    }
}
