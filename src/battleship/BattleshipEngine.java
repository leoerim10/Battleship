package battleship;

import java.io.IOException;

public class BattleshipEngine implements BattleshipSender {

   private GameStatus status;

    public BattleshipEngine() {
        this.status = GameStatus.START;
    }

    @Override
    public void sendDice(int random) throws IOException, StatusException {
        if (this.status != GameStatus.START) {
            throw new StatusException();
        }
    }

    @Override
    public void sendSet(int x, int y) throws IOException, StatusException {
        if (this.status != GameStatus.ACTIVE) {
            throw new StatusException();
        }
    }

    @Override
    public void giveUp() throws StatusException {
        if (this.status != GameStatus.ACTIVE) {
            throw new StatusException();
        }

    }

    @Override
    public void confirmation(int shipState) throws StatusException {
        if (this.status != GameStatus.PASSIVE) {
            throw new StatusException();
        }

    }
}
