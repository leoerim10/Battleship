package battleship;

import java.io.IOException;

public interface BattleshipSender {
    /**
     * allowed in START state.
     * to choose the starter
     * @param random
     * @throws IOException
     */
    void sendDice(int random) throws IOException,  StatusException;

    /**
     * allowed in ACTIVE state
     * send the coordinates to hit the ship
     * @param x
     * @param y
     * @throws IOException
     */
    void sendSet(int x, int y) throws IOException,  StatusException;

    /**
     * allowed in ACTIVE state
     * informs to giveup the game
     * @throws StatusException
     * @throws IOException
     */
    void giveUp() throws StatusException, IOException;
}
