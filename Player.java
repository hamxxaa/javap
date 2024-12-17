import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<BaseCard> hand;

    public Player() {
        hand = new ArrayList<>();
    }

    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    public List<BaseCard> getHand() {
        return hand;
    }

    public BaseCard playCard(int index, BaseCard topCard) {
        if (isCardPlayable(index, topCard)) {
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
}
