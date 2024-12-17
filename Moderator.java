public class Moderator {

    private static final int MAX_PLAYERS = 4;
    private UI ui;
    private Player player1;
    private Deck deck;
    private Player bots[];
    private static int botCount = 0;
    private boolean playing = true;

    public static void main(String[] args) {
        Moderator moderator = new Moderator();
        moderator.InitializeGame();
        moderator.PlayGame();
    }

    public Moderator() {
        this.bots = new Player[MAX_PLAYERS];
    }

    public void InitializeGame() {
        System.out.println("i");
        this.deck = new Deck();
        this.player1 = new Player();
        this.ui = new UI(player1, deck);
        while (botCount < MAX_PLAYERS) {
            addBot();
            System.out.println("ii");
        }
        System.out.println("iii");

        for (int i = 0; i < 7; i++) {
            player1.drawCard(deck);
            for (Player bot : bots) {
                bot.drawCard(deck);
            }
        }
        deck.topCard = deck.drawCard();
    }

    public void PlayGame() {
        System.out.println("Game started");
        System.out.println("Play the first card");
        int move;
        while (playing) {
            move = this.ui.getMove();
            if (move == 0) {
                player1.drawCard(deck);
            } else {
                BaseCard playedCard = player1.playCard(move - 1, deck.topCard);
                if (playedCard == null) {
                    System.out.println("This card is not playable");
                    continue;
                } else {
                    updateTopCard(playedCard);
                }
            }
        }

    }

    public void addBot() {
        if (botCount < MAX_PLAYERS) {
            bots[botCount] = new Player();
            botCount++;
        }
    }

    public void updateTopCard(BaseCard playedCard) {
        deck.topCard = playedCard;
    }
}
