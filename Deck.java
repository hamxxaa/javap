import java.util.Random;

public class Deck {
    private BaseCard[] cards;
    private static final int DECK_SIZE = 80;
    private static final int MAX_COLOR = 4;
    private String[] colors = { "Red", "Green", "Blue", "Yellow" };
    private int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private int top = 0;
    public BaseCard topCard;

    public Deck() {
        cards = new BaseCard[DECK_SIZE];
        int index = 0;
        for (String color : colors) {
            for (int val : values) {
                cards[index] = new BaseCard(color, val);
                cards[index + 1] = new BaseCard(color, val);
                index += 2;
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
