package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.airborn.gameoflife.position.PositionCalculator;

import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class PopulationTest {

    @InjectMocks
    private Population population;
    @Mock
    private PositionCalculator positionCalculator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

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
    public void shouldKillPopulationMembers() throws Exception {
        // given
        Cell cellToKill = createCellMock();
        Cell survivor = createCellMock();
        PopulationChange populationChange = new PopulationChange(ImmutableSet.<Cell>of(), ImmutableSet.of(cellToKill));

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
        PopulationChange populationChange = new PopulationChange(ImmutableSet.of(newCell), ImmutableSet.<Cell>of());

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
