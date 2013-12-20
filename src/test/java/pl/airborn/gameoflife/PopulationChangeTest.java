package pl.airborn.gameoflife;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PopulationChangeTest {

    @Test
    public void shouldName() throws Exception {
        // given
        Cell cellToBorn = mock(Cell.class);
        Collection<Cell> shouldBeBorn = Lists.newArrayList(cellToBorn);
        Cell cellToBeKilled = mock(Cell.class);
        Collection<Cell> shouldBeKill = Lists.newArrayList(cellToBeKilled);

        // when
        PopulationChange populationChange = new PopulationChange(shouldBeBorn, shouldBeKill);

        // then
        assertThat(populationChange.getShouldBeBorne()).containsOnly(cellToBorn);
        assertThat(populationChange.getShouldBeKilled()).containsOnly(cellToBeKilled);
    }
}
