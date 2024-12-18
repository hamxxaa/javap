public class Bot extends Player {
    private String name;

    // Constructor to initialize bot with deck, moderator, and name
    public Bot(Deck deck, Moderator mod, String name) {
        super(deck, mod);
        this.name = name;
    }

    /*
     * Method to choose a card to play from the bot's hand
     * 
     * @return The card that was played. Null if no card is playable
     */
    public BaseCard playCard() {
        for (int i = 0; i < getHandSize(); i++) {
            if (isCardPlayable(i, deck.topCard)) {
                BaseCard card = hand.remove(i);
                System.out.println(this.name + " played: " + card);
                return card;
            }
        }
        System.out.println(this.name + " drew a card");
        return null;
    }

    // NEEDS TO BE IMPLEMENTED*-*
    public BaseCard chooseCardToPlay() {
        return null;
        /*
         * Finds every card that is playable and chooses the best card to play
         * Can use weights and choose randomly to determine the best card to play
         */
    }

    // Override doMove method to perform a move for the bot
    @Override
    public void doMove() {
        System.out.println(this.name + "'s turn");
        if (!blocked) {
            BaseCard playedCard = playCard();
            if (playedCard == null) {
                drawCard();
            } else {
                handleCards(playedCard);
            }

        } else {
            blocked = false;
            System.out.println(this.name + " is blocked");
        }
    }

    // Override toString method to provide a string representation of the bot
    @Override
    public String toString() {
        return this.name;
    }

    // NEEDS TO BE IMPLEMENTED*-*
    @Override
    public void handleWildCard(BaseCard card) {

    }
}