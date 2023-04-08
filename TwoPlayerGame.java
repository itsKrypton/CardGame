public class TwoPlayerGame {
    public static void main(String[] args) {
        int numberOfPlayers = 2;
        Game game = new Game(numberOfPlayers);
        game.play();
    }
}