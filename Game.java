import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player[] players;
    private Card topCard;
    private int numberOfPlayers;
    private int currentPlayerIndex;
    private boolean clockwise;
    private boolean gameEnded;

    public Game(int numberOfPlayers) 
    {
        System.out.println("Initializing a new game with " + numberOfPlayers + " players...");
        this.deck = new Deck();
        this.numberOfPlayers = numberOfPlayers;
        this.players = new Player[this.numberOfPlayers];
        currentPlayerIndex = 0;
        this.clockwise = true;
        this.gameEnded = false;

        for(int i = 0; i < this.players.length; i++)
        {
            this.players[i] = new Player();

            for(int j = 0; j < 5; j++) // Each player given their first 5 starting cards
            this.players[i].addCard(this.deck.deal());
        }

        this.topCard = deck.deal();
        System.out.println();
    }

    public void play()
    {
        Scanner in = new Scanner(System.in);

        while(!gameEnded)
        {
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("Player " + (currentPlayerIndex + 1) + "'s turn: ");
            System.out.println("Top card: " + topCard.geSuit() + " " + topCard.geRank());
            System.out.println("Your hand: " + currentPlayer.getHand());
            
            boolean playedCard = false;
            while(!playedCard)
            {
                List<Card> playableCards = new ArrayList<>();
                for(Card card : currentPlayer.getHand())
                {
                    if(card.geSuit() == topCard.geSuit() || card.geRank() == topCard.geRank())
                    playableCards.add(card);
                }

                if(playableCards.isEmpty())
                {
                    System.out.println("No playable cards, drawing a card...");
                    if(deck.isEmpty())
                    {
                        System.out.println("Draw pile is empty, game ended in a draw.");
                        gameEnded = true;
                        break;
                    }

                    Card drawnCard = deck.deal();
                    System.out.println("Drawn card: " + drawnCard.geSuit() + " " + drawnCard.geRank());
                    currentPlayer.addCard(drawnCard);
                }

                else
                {
                    System.out.println("Playable cards: " + playableCards);
                    int cardIndex = 0;
                    if(playableCards.size() > 1)
                    {
                        System.out.println("Choose a card to play (enter a number from 1 to " + playableCards.size() + "):");
                        cardIndex = in.nextInt() - 1;
                    }

                    Card cardToPlay = playableCards.get(cardIndex);
                    System.out.println("Playing " + cardToPlay.geSuit() + " " + cardToPlay.geRank());
                    topCard = cardToPlay;
                    currentPlayer.removeCard(cardToPlay);
                    playedCard = true;

                    if(currentPlayer.getHand().isEmpty())
                    {
                        System.out.println("Player " + (currentPlayerIndex + 1) + " has won!");
                        gameEnded = true;
                        continue;
                    }

                    if(cardToPlay.isActionCard())
                    performAction(cardToPlay.getActionType());
                }
            }

            if(!gameEnded)
            currentPlayerIndex = (currentPlayerIndex + 1) % this.numberOfPlayers;

            System.out.println();
        }

        in.close();
    }

    private void performAction(ActionType actionType)
    {
        switch(actionType)
        {
            case SKIP:
            {
                System.out.println("Skipping next player's turn");

                if(this.clockwise)
                this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.numberOfPlayers;

                else
                this.currentPlayerIndex = (this.currentPlayerIndex - 1 + numberOfPlayers) % this.numberOfPlayers;

                break;
            }

            case REVERSE:
            {
                System.out.println("Reversing turn order");
                this.clockwise = !this.clockwise;
                break;
            }

            case DRAW_TWO:
            {
                System.out.println("Next player draws two cards");
                int nextPlayerIndex = -1;

                if(this.clockwise)
                nextPlayerIndex = (this.currentPlayerIndex + 1) % this.numberOfPlayers;

                else
                nextPlayerIndex = (this.currentPlayerIndex - 1 + numberOfPlayers) % this.numberOfPlayers;

                Player nextPlayer = players[nextPlayerIndex];
                for(int penalty = 0; penalty < 2; penalty++)
                {
                    if(deck.isEmpty())
                    {
                        System.out.println("Draw pile is empty, game ended in a draw.");
                        gameEnded = true;
                        break;
                    }
                    nextPlayer.addCard(deck.deal());
                }
                performAction(ActionType.SKIP);
                break;
            }

            case DRAW_FOUR:
            {
                System.out.println("Next player draws four cards");
                int nextPlayerIndex = -1;

                if(this.clockwise)
                nextPlayerIndex = (this.currentPlayerIndex + 1) % this.numberOfPlayers;

                else
                nextPlayerIndex = (this.currentPlayerIndex - 1 + numberOfPlayers) % this.numberOfPlayers;

                Player nextPlayer = players[nextPlayerIndex];
                for(int penalty = 0; penalty < 4; penalty++)
                {
                    if(deck.isEmpty())
                    {
                        System.out.println("Draw pile is empty, game ended in a draw.");
                        gameEnded = true;
                        break;
                    }
                    nextPlayer.addCard(deck.deal());
                }
                performAction(ActionType.SKIP);
                break;
            }

            default: // No action
        }
    }
}
