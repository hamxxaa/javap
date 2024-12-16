import java.util.Random;

public class Deck {
    private BaseCard[] cards;
    private static final int DECK_SIZE = 108;
    private static final int MAX_COLOR = 4;
    private String[] colors = { "Red", "Green", "Blue", "Yellow" };
    private int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private int top = 0;

    public Deck() {
        cards = new BaseCard[DECK_SIZE];
        int index = 0;
        for (int i = 0; i < MAX_COLOR; i++) {
            for (int j = 0; j < values.length; j++) {
                cards[index] = new BaseCard(colors[i], values[j]);
                index++;
            }
        }
        shuffleDeck();
    }

    private void shuffleDeck() {
        Random random = new Random();
        for (int i = DECK_SIZE - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            BaseCard temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }
    public BaseCard drawCard() {
        if (top < DECK_SIZE) {
            return cards[top++];
        } else {
            throw new IllegalStateException("No cards left in the deck");
        }
    }
}
