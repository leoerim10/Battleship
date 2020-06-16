package battleship;

import java.io.IOException;

public class Shortcut implements Sender {
    private Receiver receiver;

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void sendDice(int random) throws IOException, StatusException {
        this.receiver.receiveDice(random);
    }

    @Override
    public void sendSet(int x, int y) throws IOException, StatusException {
    this.receiver.receiveSet(x,y);
    }

    @Override
    public void senderGiveUp() throws StatusException, IOException {
    this.receiver.receiverGiveUp();
    }

    @Override
    public void senderShipStatus(int shipState) throws StatusException, IOException {
    this.receiver.receiverShipStatus(shipState);
    }
}
