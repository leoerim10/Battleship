package battleship;

public class BattleshipException extends Exception {
    public BattleshipException(String message) {
        super("wrong parameter entered, make sure the defined one is entered.");
    }
}
