package battleship.protocolBinding;

import battleship.BattleshipException;
import battleship.Receiver;
import battleship.StatusException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamBindingReceiver extends Thread {
    private final DataInputStream dis;
    private final Receiver receiver;

    public StreamBindingReceiver(InputStream is, Receiver receiver) {
        this.dis = new DataInputStream(is);
        this.receiver = receiver;
    }

    public void readDice() throws IOException, StatusException {
        int random = this.dis.readInt();
        this.receiver.receiveDice(random);
    }

    public void readSet() throws IOException, StatusException {
        int x = this.dis.readInt();
        int y = this.dis.readInt();
        try {
            this.receiver.receiveSet(x, y);
        } catch (BattleshipException e) {
            System.err.println("cannot execute set - don't inform sender - error not part of protocol: "
                    + e.getLocalizedMessage());
        }
    }


    public void readGiveUp() throws IOException, StatusException{
        this.receiver.receiverGiveUp();
    }

    public void readShipStatus() throws IOException, StatusException{
        int shipState = dis.readInt();
        this.receiver.receiverShipStatus(shipState);
    }

    public void run() {
        boolean again = true;
        while(again) {

            try {
                int command = this.dis.readInt();

                switch(command) {
                    case StreamBinding.DICE:
                        this.readDice();
                        break;
                    case StreamBinding.SET:
                        this.readSet();
                        break;
                    case StreamBinding.GIVE_UP:
                        this.readGiveUp();
                        break;
                    case StreamBinding.SHIP_STATUS:
                        this.readShipStatus();
                        break;
                    default:
                        again = false;
                        System.err.println("Unknown command: "+ command);

                }
            } catch (IOException e) {
                System.err.println("IOException: " + e.getLocalizedMessage());
                again = false;
            } catch (StatusException e) {
                System.err.println("StatusException: "+e.getLocalizedMessage());
                again = false;
            }
        }

    }
}
