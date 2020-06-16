package battleship;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BattleshipTest {

    @Test
    void usageTest() throws BattleshipException, StatusException, IOException {
        Shortcut sender1 = new Shortcut();
        GameEngine game1 = new GameEngine(sender1);

        Shortcut sender2 = new Shortcut();
        GameEngine game2 = new GameEngine(sender2);

        //connect both games
        sender1.setReceiver(game2);
        sender1.setReceiver(game1);

        // test methods
        game2.throwDice();
        game1.throwDice();

        Usage activeGame = game1.isActive() ? game1 : game2;

        activeGame.set(0,0);
        Assert.assertFalse(game1.isActive());

    }
}
