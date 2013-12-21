package pl.airborn.gameoflife;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class PopulationTest {

    private Population population = new Population();

    @Test
    public void shouldReturnPopulationMembers() throws Exception {
        // given
        Cell cell1 = new Cell(mock(Position.class));
        population.addCell(cell1);
        Cell cell2 = new Cell(mock(Position.class));
        population.addCell(cell2);

        // when
        Collection<Cell> actual = population.getMembers();

        // then
        assertThat(actual).containsOnly(cell1, cell2);
    }

    @Test
    public void shouldCheckIfMemberIsAlive_true() throws Exception {
        // given
        Position position = mock(Position.class);
        Cell cell1 = new Cell(position);
        population.addCell(cell1);

        // when
        boolean actual = population.isAlive(position);

        // then
        assertThat(actual).isTrue();
    }

    @Test
    public void shouldCheckIfMemberIsAlive_false() throws Exception {
        // given
        Position position = mock(Position.class);

        // when
        boolean actual = population.isAlive(position);

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @Parameters({"0", "1", "2", "3", "4", "5", "6", "7", "8"})
    public void shouldReturnNumberOfLivingNeighbours(int param) throws Exception {
        // given
        Position cellPosition = mock(Position.class);
        Set<Position> neighbours = getNeighbours();
        when(cellPosition.getNeighboursPositions()).thenReturn(neighbours);
        Iterable<Position> expectedPositions = Iterables.limit(neighbours, param);
        for (Position expectedPosition : expectedPositions) {
            population.addCell(new Cell(expectedPosition));
        }
        population.addCell(new Cell(mock(Position.class)));
        population.addCell(new Cell(mock(Position.class)));
        population.addCell(new Cell(mock(Position.class)));

        // when
        int actual = population.getNumberOfLivingNeighbours(cellPosition);

        // then
        assertThat(actual).isEqualTo(param);
    }

    private Set<Position> getNeighbours() {
        Position neighbour11 = mock(Position.class);
        Position neighbour12 = mock(Position.class);
        Position neighbour13 = mock(Position.class);
        Position neighbour21 = mock(Position.class);
        Position neighbour23 = mock(Position.class);
        Position neighbour31 = mock(Position.class);
        Position neighbour32 = mock(Position.class);
        Position neighbour33 = mock(Position.class);

        return Sets.newHashSet(
                neighbour11,
                neighbour12,
                neighbour13,
                neighbour21,
                neighbour23,
                neighbour31,
                neighbour32,
                neighbour33);
    }

    @Test
    public void shouldKillPopulationMembers() throws Exception {
        // given
        Cell cellToKill = createCellMock();
        Cell survivor = createCellMock();
        PopulationChange populationChange = mock(PopulationChange.class);
        when(populationChange.getShouldBeKilled()).thenReturn(Sets.newHashSet(cellToKill));

        population.addCell(cellToKill);
        population.addCell(survivor);

        // when
        population.applyPopulationChanges(populationChange);

        // then
        assertThat(population.getMembers()).containsOnly(survivor);
    }

    @Test
    public void shouldAddMemberToPopulation() throws Exception {
        // given
        Cell oldCell = createCellMock();
        Cell newCell = createCellMock();
        PopulationChange populationChange = mock(PopulationChange.class);
        when(populationChange.getShouldBeBorne()).thenReturn(Sets.newHashSet(newCell));

        population.addCell(oldCell);

        // when
        population.applyPopulationChanges(populationChange);

        // then
        assertThat(population.getMembers()).containsOnly(oldCell, newCell);
    }

    private Cell createCellMock() {
        return mock(Cell.class, withSettings().defaultAnswer(RETURNS_DEEP_STUBS));
    }

}
