package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PopulationChangeTest {

    @Test
    public void shouldName() throws Exception {
        // given
        Cell cellToBorn = mock(Cell.class);
        ImmutableSet<Cell> shouldBeBorn = ImmutableSet.of(cellToBorn);
        Cell cellToBeKilled = mock(Cell.class);
        ImmutableSet<Cell> shouldBeKill = ImmutableSet.of(cellToBeKilled);

        // when
        PopulationChange populationChange = new PopulationChange(shouldBeBorn, shouldBeKill);

        // then
        assertThat(populationChange.getShouldBeBorne()).containsOnly(cellToBorn);
        assertThat(populationChange.getShouldBeKilled()).containsOnly(cellToBeKilled);
    }
}
