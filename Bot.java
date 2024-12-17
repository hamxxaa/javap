public class Bot extends Player {
    public Bot(Deck deck, Moderator mod) {
        super(deck, mod);
    }

    public BaseCard playCard() {
        for (int i = 0; i < getHandSize(); i++) {
            if (isCardPlayable(i, deck.topCard)) {
                BaseCard card = getHand().remove(i);
                System.out.println("Bot played: " + card);
                return card;
            } else {
                System.out.println("Bot drew a card");
                return null;
            }
        }
        return null;
    }

    @Override
    public void doMove() {
        System.out.println("Bot's turn");
        if (!blocked) {
            BaseCard playedCard = playCard();
            if (playedCard == null) {
                drawCard();
            } else {
                this.deck.updateTopCard(playedCard);
            }
        } else {
            blocked = false;
            System.out.println("Bot is blocked");
        }
    }

    @Override
    public String toString() {
        return "Bot";
    }
}
