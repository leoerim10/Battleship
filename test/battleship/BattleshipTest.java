package battleship;

import org.junit.Assert;
import org.junit.Test;

public class BattleshipTest {

    @Test
    void usageTest() throws BattleshipException, StatusException {
        Usage game1 = new GameEngine();
        game1.throwDice();

        Usage game2 = new GameEngine();
        game2.throwDice();

        Usage activeGame = game1.isActive() ? game1 : game2;

        activeGame.set(0,0);
        Assert.assertFalse(game1.isActive());

    }
}
