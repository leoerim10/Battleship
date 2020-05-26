package battleship;

import java.io.IOException;
import java.util.Random;

public class BattleshipEngine implements BattleshipReceiver, BattleshipSender {
    public static int UNDEFINED_DICE = -1;
    private GameStatus status;
    private int sentDice = UNDEFINED_DICE;

    public BattleshipEngine() {
        this.status = GameStatus.START;
    }

    @Override
    public void receiveDice(int random) throws IOException, StatusException {
        if (this.status != GameStatus.START && this.status != GameStatus.DICE_SENT){
            throw new StatusException();
        }
        // larger number -> ACTIVE, smaller one -> PASSIVE, repeat if the numbers are same
        if (this.status == GameStatus.DICE_SENT){
            if(random == this.sentDice){
                this.status = GameStatus.START;
            }else if(random < this.sentDice){
                this.status = GameStatus.ACTIVE;
            }else{
                this.status = GameStatus.PASSIVE;
            }
        }
    }

    @Override
    public void receiveSet(int x, int y) throws IOException, StatusException {
        if (this.status != GameStatus.PASSIVE) {
            throw new StatusException();
        }
        // if the received set is the set to sink the final ship then Gamestatus.Lost
    }

    @Override
    public void sendDice(int random) throws IOException, StatusException {
        if (this.status != GameStatus.START) {
            throw new StatusException();
        }
        Random number = new Random();
        this.sentDice = number.nextInt();
        // sends the number to the receiver
        this.status = GameStatus.DICE_SENT;
    }

    @Override
    public void sendSet(int x, int y) throws IOException, StatusException {
        if (this.status != GameStatus.ACTIVE) {
            throw new StatusException();
        }
        // if the sent set is the set to sink the final ship then Gamestatus.Won
    }


    @Override
    public void giveUp() throws IOException, StatusException {
        if (this.status != GameStatus.ACTIVE) {
            throw new StatusException();
        }

    }

    @Override
    public void confirmation(int shipState) throws IOException, StatusException {
        if (this.status != GameStatus.PASSIVE) {
            throw new StatusException();
        }

    }
}
