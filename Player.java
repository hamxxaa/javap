import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<BaseCard> hand;
    public boolean blocked = false;
    public Deck deck;
    private UI ui;
    private Moderator mod;

    public Player(Deck deck, Moderator mod) {
        hand = new ArrayList<>();
        this.deck = deck;
        this.mod = mod;
    }

    public void drawCard() {
        BaseCard drawnCard = deck.drawCard();
        this.hand.add(drawnCard);
    }

    public List<BaseCard> getHand() {
        return hand;
    }

    public BaseCard playCard(int index) {
        if (isCardPlayable(index, deck.topCard)) {
            BaseCard card = hand.remove(index);
            return card;
        } else {
            return null;
        }
    }

    public boolean isCardPlayable(int cardToPlay, BaseCard topCard) {
        return hand.get(cardToPlay).getColor().equals(topCard.getColor())
                || hand.get(cardToPlay).getValue() == topCard.getValue();
    }

    public int getHandSize() {
        return hand.size();
    }

    public Deck getDeck() {
        return deck;
    }

    public void doMove() {
        int move;
        if (!blocked) {
            move = this.ui.getMove();
            if (move == 0) {
                drawCard();
            } else {
                BaseCard playedCard = playCard(move - 1);
                if (playedCard == null) {
                    System.out.println("This card is not playable");
                } else if (playedCard.getValue() == 10) {
                    mod.blockPlayer(mod.getNextPlayer(this));
                    deck.updateTopCard(playedCard);
                }
            }
        } else {
            blocked = false;
            System.out.println("Player is blocked");
        }
    }

    public String toString() {
        return "Player";
    }

    public void createUI() {
        this.ui = new UI(this);
    }
}
