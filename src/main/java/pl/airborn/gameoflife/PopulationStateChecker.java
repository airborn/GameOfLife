package pl.airborn.gameoflife;

public interface PopulationStateChecker {
    boolean isAlive(Position position);

    int getNumberOfLivingNeighbours(Position position);
}
