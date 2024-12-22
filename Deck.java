import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<BaseCard> cards;
    private static final int DECK_SIZE = 112;
    public String[] colors = { "Red", "Green", "Blue", "Yellow" };
    private int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };// 10 is skip, 11 is reverse, 12 is
                                                                                // draw two, 13 is wild, 14 is wild draw
                                                                                // four
    private int top = 0; // Index of the top card in the deck
    public BaseCard topCard; // The top card in the played deck
    public List<BaseCard> playedDeck; // The played deck

    // Constructor to initialize the deck with cards
    public Deck() {
        cards = new ArrayList<>(DECK_SIZE);
        playedDeck = new ArrayList<>();
        for (String color : colors) {
            for (int i = 0; i < 13; i++) {
                cards.add(new BaseCard(color, values[i]));
                cards.add(new BaseCard(color, values[i]));
            }
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new BaseCard("Wild", 13));
            cards.add(new BaseCard("Wild", 14));
        }
        shuffleDeck();
    }

    // Method to shuffle the deck
    private void shuffleDeck() {
        Random random = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            BaseCard temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    /*
     * Method to draw the top card from the deck and increment the top index
     * 
     * @Return Top card
     */
    public BaseCard drawCard() {
        if (top < cards.size()) {
            return cards.get(top++);
        } else if (playedDeck.size() == 0) {
            throw new IllegalStateException("Both deck and played deck are empty");
        } else {
            reusePlayedDeck();
            return cards.get(top++);
        }
    }

    /*
     * Method to update the top card in the played deck
     * 
     * @param playedCard The card that was played
     */
    public void updateTopCard(BaseCard playedCard) {
        if (this.topCard != null) {
            playedDeck.add(topCard);
        }
        this.topCard = playedCard;
    }

    // Method to reuse the played deck when the deck is empty
    public void reusePlayedDeck() {
        cards.clear();
        cards.addAll(playedDeck);
        playedDeck.clear();
        top = 0;
        shuffleDeck();
    }
}
