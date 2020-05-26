package battleship;

import java.io.IOException;

public interface BattleshipReceiver {
    /**
     * allowed in START state
     * leads either to active or to passive game state
     * @param random
     * @throws IOException
     */
    void receiveDice(int random) throws IOException;

    /**
     * allowed in PASSIVE state
     * @param x
     * @param y
     * @throws IOException
     */
    void receiveSet(int x, int y) throws IOException;

    /**
     * notify to give up if wants to quit
     */
    void giveUp();

    /**
     *
     */
    void confirmation(int shipState);
}
