public class ThreePlayerGame {
    public static void main(String[] args) {
        int numberOfPlayers = 3;
        Game game = new Game(numberOfPlayers);
        game.play();
    }
}
