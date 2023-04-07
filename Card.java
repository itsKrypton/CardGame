public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public Suit geSuit()
    {
        return this.suit;
    }

    public Rank geRank()
    {
        return this.rank;
    }

    @Override
    public String toString() {
        return this.suit.toString() + " "  + this.rank.toString();
    }
}
