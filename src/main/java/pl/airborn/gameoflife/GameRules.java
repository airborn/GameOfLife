package pl.airborn.gameoflife;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.rules.KillRules;
import pl.airborn.gameoflife.rules.NewbornsRules;

import java.util.Collection;
import java.util.Set;

@Singleton
public class GameRules {

    private final KillRules killRules;
    private final NewbornsRules newbornsRules;

    @Inject
    public GameRules(KillRules killRules, NewbornsRules newbornsRules) {
        this.killRules = killRules;
        this.newbornsRules = newbornsRules;
    }

    public PopulationChange apply(final Population currentPopulation) {
        Collection<Cell> survivors = killRules.getKilled(currentPopulation);
        Set<Cell> newborns = newbornsRules.getNewborns(currentPopulation);
        return new PopulationChange(newborns, survivors);
    }
}
