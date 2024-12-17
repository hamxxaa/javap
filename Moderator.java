public class Moderator {

    private static final int MAX_PLAYERS = 4;
    private Player player1;
    private Deck deck;
    private Player queue[];
    private static int botCount = 1;
    private boolean playing = true;

    public static void main(String[] args) {
        Moderator moderator = new Moderator();
        moderator.InitializeGame();
        moderator.PlayGame();
    }

    public Moderator() {
        this.queue = new Player[MAX_PLAYERS];
    }

    public void InitializeGame() {
        this.deck = new Deck();
        this.player1 = new Player(deck, this);
        queue[0] = player1;
        player1.createUI();
        while (botCount < MAX_PLAYERS) {
            addBot();
        }
        for (int i = 0; i < 7; i++) {
            for (Player player : queue) {
                player.drawCard();
            }
        }
        deck.updateTopCard(deck.drawCard());
    }

    public void PlayGame() {
        System.out.println("Game started");
        while (playing) {
            for (Player player : queue) {
                player.doMove();
                if (player.getHandSize() == 0) {
                    playing = false;
                    System.out.println(player + " wins!");
                    break;
                }
            }
        }
    }

    public void addBot() {
        if (botCount < MAX_PLAYERS) {
            queue[botCount] = new Bot(this.deck, this);
            botCount++;
        }
    }

    public Player getNextPlayer(Player p) {
        for (int i = 0; i < MAX_PLAYERS; i++) {
            if (queue[i] == p) {
                return queue[(i + 1) % MAX_PLAYERS];
            }
        }
        return null;
    }

    public void blockPlayer(Player p) {
        p.blocked = true;
    }
}
