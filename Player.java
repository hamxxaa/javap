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

    public void playCard(int index) {
        hand.remove(index);
    }

    public int getHandSize() {
        return hand.size();
    }
}
