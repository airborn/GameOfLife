package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.airborn.gameoflife.position.Position;
import pl.airborn.gameoflife.position.PositionCalculator;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class PopulationTest {

    @InjectMocks
    private Population population;
    @Mock
    private PositionCalculator positionCalculator;
    @Mock
    private CellFactory cellFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnPopulationMembers() throws Exception {
        // given
        Position position1 = mock(Position.class);
        population.createCellAt(position1);
        Position position2 = mock(Position.class);
        population.createCellAt(position2);

        // when
        ImmutableSet<Position> actual = population.getMembersPositions();

        // then
        assertThat(actual).containsOnly(position1, position2);
    }

    @Test
    public void shouldCreateCell() throws Exception {
        // given
        Position position = mock(Position.class);
        population.createCellAt(position);

        Cell cell = mock(Cell.class);

        when(cellFactory.createCell(position)).thenReturn(cell);
        population.createCellAt(position);

        // when
        ImmutableSet<Cell> actual = population.getMembers();

        // then
        assertThat(actual).containsOnly(cell);
    }

    @Test
    public void shouldCheckIfMemberIsAlive_true() throws Exception {
        // given
        Position position = mock(Position.class);
        population.createCellAt(position);

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
        Position positionToKill = mock(Position.class);
        Position survivor = mock(Position.class);
        PopulationChange populationChange = new PopulationChange(ImmutableSet.<Position>of(), ImmutableSet.of(positionToKill));

        population.createCellAt(positionToKill);
        population.createCellAt(survivor);

        // when
        population.applyPopulationChanges(populationChange);

        // then
        assertThat(population.getMembersPositions()).containsOnly(survivor);
    }

    @Test
    public void shouldAddMemberToPopulation() throws Exception {
        // given
        Position oldPosition = mock(Position.class);
        Position newPosition = mock(Position.class);
        PopulationChange populationChange = new PopulationChange(ImmutableSet.of(newPosition), ImmutableSet.<Position>of());

        population.createCellAt(oldPosition);

        // when
        population.applyPopulationChanges(populationChange);

        // then
        assertThat(population.getMembersPositions()).containsOnly(oldPosition, newPosition);
    }
}
