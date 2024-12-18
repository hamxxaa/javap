import java.util.Random;

public class Deck {
    private BaseCard[] cards;
    private static final int DECK_SIZE = 96;
    private String[] colors = { "Red", "Green", "Blue", "Yellow" };
    private int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };// 10 is skip, 11 is reverse
    private int top = 0; // Index of the top card in the deck
    public BaseCard topCard; // The top card in the played deck

    // Constructor to initialize the deck with cards
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

    // Method to shuffle the deck
    private void shuffleDeck() {
        Random random = new Random();
        for (int i = DECK_SIZE - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            BaseCard temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    /*
     * Method to draw the top card from the deck and increment the top index
     * 
     * @Return Top card
     */
    public BaseCard drawCard() {
        if (top < DECK_SIZE) {
            return cards[top++];
        } else {
            reusePlayedDeck();// NEEDS TO BE IMPLEMENTED
            return cards[top++];
        }
    }

    /*
     * Method to update the top card in the played deck
     * 
     * @param playedCard The card that was played
     */
    public void updateTopCard(BaseCard playedCard) {
        this.topCard = playedCard;
    }

    // NEEDS TO BE IMPLEMENTED*-*
    public void reusePlayedDeck() {
        /*
         * Gets the played deck and shuffles it to be used as the new deck but the top
         * card is the last card played
         * Should not use every card because it would duplicate cards that players holds
         * Need to find a way to only use the cards that are not in the players hands
         * Maybe a method that checks if a card is in the players hand and moves it to
         * the top of the deck and sets top to card that is below it
         */
    }
}
