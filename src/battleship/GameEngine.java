package battleship;

import java.io.IOException;
import java.util.Random;

public class GameEngine implements Receiver, Usage {
    public static int UNDEFINED_DICE = -1;
    private final Sender sender;
    private GameStatus status;
    private int sentDice = UNDEFINED_DICE;
    public final int DIM = 10;


    GameBoardStatus[][] myBoard = new GameBoardStatus[DIM][DIM];
    GameBoardStatus[][] opponentBoard = new GameBoardStatus[DIM][DIM];

    public GameEngine(Sender sender) {
        this.status = GameStatus.START;
        this.sender = sender;
        for (int i = 0; i < DIM; i++){
            for( int j = 0; j < DIM; j++ ){
                this.myBoard[i][j] = GameBoardStatus.EMPTY;
            }
        }

        for (int i = 0; i < DIM; i++){
            for( int j = 0; j < DIM; j++ ){
                this.opponentBoard[i][j] = GameBoardStatus.EMPTY;
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////                          remote engine Support                               /////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

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
    public void receiverShipStatus(int shipState) throws IOException, StatusException {
        if (this.status != GameStatus.PASSIVE) {
            throw new StatusException();
        }

    }

    @Override
    public void receiverGiveUp() throws StatusException, IOException {
        if (this.status != GameStatus.ACTIVE) {
            throw new StatusException();
        }
    }




   /* public void sendDice(int random) throws IOException, StatusException {
        if (this.status != GameStatus.START) {
            throw new StatusException();
        }
        Random number = new Random();
        this.sentDice = number.nextInt();
        // sends the number to the receiver
        this.status = GameStatus.DICE_SENT;
    }*/


    public void sendSet(int x, int y) throws IOException, StatusException {
        if (this.status != GameStatus.ACTIVE) {
            throw new StatusException();
        }
        // if the sent set is the set to sink the final ship then Gamestatus.Won
    }



    public void senderGiveUp() throws IOException, StatusException {
        if (this.status != GameStatus.ACTIVE) {
            throw new StatusException();
        }
    }


    public void senderShipStatus(int shipState) throws StatusException, IOException {
        if (this.status != GameStatus.PASSIVE) {
            throw new StatusException();
        }
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////                          User Interface Support                               /////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void throwDice() throws StatusException, IOException {
        if (this.status != GameStatus.START) {
            throw new StatusException();
        }
        Random number = new Random();
        this.sentDice = number.nextInt();

        // sends the number to the receiver via sender
        this.sender.sendDice(this.sentDice);
        this.status = GameStatus.DICE_SENT;
    }

    @Override
    public boolean isActive() {
        return this.status == GameStatus.ACTIVE;
    }

    @Override
    public void set(int x, int y) throws BattleshipException, StatusException {

    }
}
