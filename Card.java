public class Card {
    private Rank rank;
    private Suit suit;
    private boolean isActionCard;
    private ActionType actionType;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
        this.isActionCard = rank.equals(Rank.ACE) || rank.equals(Rank.JACK) || rank.equals(Rank.QUEEN) || rank.equals(Rank.KING);

        if(isActionCard)
        {
            if(rank.equals(Rank.ACE))
            this.actionType = ActionType.SKIP;

            else if(rank.equals(Rank.KING))
            this.actionType = ActionType.REVERSE;

            else if(rank.equals(Rank.QUEEN))
            this.actionType = ActionType.DRAW_TWO;

            else
            this.actionType = ActionType.DRAW_FOUR;
        }

        else
        this.actionType = ActionType.NONE;
    }

    public Suit geSuit()
    {
        return this.suit;
    }

    public Rank geRank()
    {
        return this.rank;
    }

    public boolean isActionCard()
    {
        return this.isActionCard;
    }

    public ActionType getActionType() 
    {
        return this.actionType;
    }

    @Override
    public String toString() {
        return this.suit.toString() + " "  + this.rank.toString();
    }
}
