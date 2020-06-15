package battleship;

public interface Usage {
    /**
     * figure out who starts the game
     */
    void throwDice() throws StatusException;

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
