package battleship;

import java.io.IOException;

public interface Usage {
    /**
     * figure out who starts the game
     */
    void throwDice() throws StatusException, IOException;

    /**
     *
     * @return true if active player can set the coordinates
     */
    boolean isActive();

    /**
     * set the coordinates at position x,y
     * @param x range 0 to 9
     * @param y range 0 to 9
     */
    void set(int x, int y) throws BattleshipException, StatusException;
}
