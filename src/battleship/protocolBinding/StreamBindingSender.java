package battleship.protocolBinding;

import battleship.Sender;
import battleship.StatusException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StreamBindingSender implements Sender {
    private final DataOutputStream dos;

    public StreamBindingSender(OutputStream os) {
        this.dos = new DataOutputStream(os);
    }

    @Override
    public void sendDice(int random) throws IOException, StatusException {
        this.dos.writeInt(StreamBinding.DICE);
        this.dos.writeInt(random);
    }

    @Override
    public void sendSet(int x, int y) throws IOException, StatusException {
        this.dos.writeInt(StreamBinding.SET);
        this.dos.writeInt(x);
        this.dos.writeInt(y);
    }

    @Override
    public void senderGiveUp() throws StatusException, IOException {
        this.dos.writeInt(StreamBinding.GIVE_UP);
    }

    @Override
    public void senderShipStatus(int shipState) throws StatusException, IOException {
        this.dos.writeInt(StreamBinding.SHIP_STATUS);
        this.dos.writeInt(shipState);
    }
}
