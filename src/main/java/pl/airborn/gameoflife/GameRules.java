package pl.airborn.gameoflife;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.airborn.gameoflife.rules.KillRules;
import pl.airborn.gameoflife.rules.NewbornsRules;

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
        ImmutableSet<Position> killed = killRules.getKilled(currentPopulation);
        ImmutableSet<Position> newborns = newbornsRules.getNewborns(currentPopulation);
        return new PopulationChange(newborns, killed);
    }
}
