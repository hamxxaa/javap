public class Moderator {
    public static void main(String[] args) {
        Moderator moderator = new Moderator();
        moderator.InitializeGame();
    }

    public void InitializeGame() {
        Deck deck = new Deck();
        Player player1 = new Player();
        UI ui = new UI(player1);
        Player bot1 = new Player();
        for (int i = 0; i < 7; i++) {
            player1.drawCard(deck);
            bot1.drawCard(deck);
        }
    }

    public void PlayGame() {
        System.out.println("Game started");
        System.out.println("Play the first card");


    }
}
