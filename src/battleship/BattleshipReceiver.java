package battleship;

import java.io.IOException;

public interface BattleshipReceiver {
    /**
     * allowed in START state
     * leads either to active or to passive game state
     * @param random
     * @throws IOException
     */
    void receiveDice(int random) throws IOException, StatusException;

    /**
     * allowed in PASSIVE state
     * receives the coordinates for the ships from the sender
     * @param x
     * @param y
     * @throws IOException
     */
    void receiveSet(int x, int y) throws IOException, StatusException;


    /**
     * allowed in PASSIVE state
     * confirms the sent coordinates either it was hit, miss or sunk
     * @param shipState
     * @throws StatusException
     * @throws IOException
     */
    void confirmation(int shipState) throws StatusException, IOException;
}
