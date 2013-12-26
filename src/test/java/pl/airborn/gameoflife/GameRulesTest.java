package pl.airborn.gameoflife;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.airborn.gameoflife.rules.KillRules;
import pl.airborn.gameoflife.rules.NewbornsRules;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameRulesTest {

    @InjectMocks
    private GameRules gameRules;
    @Mock
    private KillRules killRules;
    @Mock
    private NewbornsRules newbornsRules;
    @Mock
    private Population population;

    @Test
    public void shouldReturnPopulationChanges() throws Exception {
        // given
        Set<Cell> newborns = Sets.newHashSet(mock(Cell.class));
        when(newbornsRules.getNewborns(population)).thenReturn(newborns);
        Set<Cell> killed = Sets.newHashSet(mock(Cell.class));
        when(killRules.getKilled(population)).thenReturn(killed);

        // when
        PopulationChange actual = gameRules.apply(population);

        // then
        assertThat(actual.getShouldBeBorne()).isEqualTo(newborns);
        assertThat(actual.getShouldBeKilled()).isEqualTo(killed);
    }
}
