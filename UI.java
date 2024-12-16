import java.util.Scanner;

public class UI {

    private Player player;
    private Scanner scanner;

    UI(Player player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    public void displayHand() {
        System.out.println("Player's hand:");
        int i = 1;
        for (BaseCard card : player.getHand()) {
            System.out.println(i + ": " + card);
            i++;
        }
        System.out.println(i + ": Draw a card");
    }

    public int getMove() {
        displayHand();
        System.out.println("Enter the card you want to play:");
        while (true) {
            int move = scanner.nextInt();
            if (move >= 1 && move <= player.getHandSize() + 1) {
                return move;
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }

    }
}
