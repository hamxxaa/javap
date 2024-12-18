import java.util.Scanner;

public class UI {

    private Player player;
    private Scanner scanner;
    private Deck deck;

    // Constructor to initialize UI with player
    UI(Player player) {
        this.player = player;
        this.deck = player.deck;
        this.scanner = new Scanner(System.in);
    }

    // Method to display the player's hand
    public void displayHand() {
        System.out.println("Player's hand:");
        System.out.println("0: Draw a card");
        int i = 1;
        for (BaseCard card : player.hand) {
            System.out.println(i + ": " + card);
            i++;
        }
    }

    /*
     * Method to get the move from the player
     * 
     * @return the move as index-1 of the card in the hand. 0 is for drawing a card
     */
    public int getMove() {
        displayHand();
        System.out.println("Top card: " + deck.topCard);
        System.out.println("Enter the card you want to play:");
        while (true) {
            int move = scanner.nextInt();
            System.out.println("move:" + move + "handsize: " + player.getHandSize());
            if (move >= 0 && move <= player.getHandSize()) {
                return move;
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }
}
