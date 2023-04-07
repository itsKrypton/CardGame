import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card)
    {
        this.hand.add(card);
    }

    public void removeCard(Card card)
    {
        this.hand.remove(card);
    }

    public List<Card> getHand()
    {
        return this.hand;
    }
}
