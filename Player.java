import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<BaseCard> hand;
    public boolean blocked = false;
    public Deck deck;
    private UI ui;
    public Moderator mod;

    // Constructor to initialize player with deck and moderator
    public Player(Deck deck, Moderator mod) {
        hand = new ArrayList<>();
        this.deck = deck;
        this.mod = mod;
    }

    // Method to draw a card from the deck and add it to the player's hand
    public void drawCard() {
        BaseCard drawnCard = deck.drawCard();
        this.hand.add(drawnCard);
    }

    /*
     * Method to play a card from the player's hand
     * 
     * @param index The index of the card to play
     * 
     * @return The card that was played. Null if the card is not playable
     */
    public BaseCard playCard(int index) {
        if (isCardPlayable(index, deck.topCard)) {
            BaseCard card = hand.remove(index);
            return card;
        } else {
            return null;
        }
    }

    /*
     * Method to check if a card is playable
     * 
     * @param cardToPlay The index of the card to play
     * 
     * @param topCard The top card in the played deck
     * 
     * @return True if the card is playable, false otherwise
     */
    public boolean isCardPlayable(int cardToPlay, BaseCard topCard) {
        return hand.get(cardToPlay).getColor().equals(topCard.getColor())
                || hand.get(cardToPlay).getValue() == topCard.getValue();
    }

    // Method to get the size of the hand
    public int getHandSize() {
        return hand.size();
    }

    // Method to get a move from player and play the card
    public void doMove() {
        int move;
        if (!blocked) {
            move = this.ui.getMove();
            if (move == 0) {
                drawCard();
            } else {
                BaseCard playedCard = playCard(move - 1);
                if (playedCard == null) {
                    System.out.println("This card is not playable");
                } else {
                    handleCards(playedCard);
                }
            }
        } else {
            blocked = false;
            System.out.println("Player is blocked");
        }
    }

    // Override toString method to provide a string representation of the player
    @Override
    public String toString() {
        return "Player";
    }

    // Method to create UI for non-bot player
    public void createUI() {
        this.ui = new UI(this);
    }

    /*
     * Method to play a card and handle special cards
     * 
     * @param playedCard The card that was played
     */
    public void handleCards(BaseCard playedCard) {
        if (playedCard.getValue() == 10) {
            mod.blockPlayer(mod.getNextPlayer(this));
        } else if (playedCard.getValue() == 11) {
            mod.queue.reverse();
        } else if (playedCard.getValue() == 12) {
            handleWildCard(playedCard);// NEEDS TO BE IMPLEMENTED-----------------------------------------------------------
        } else if (playedCard.getValue() == 13) {
            /*
             * Makes next player draw two cards and skips the next player
             */
        } else if (playedCard.getValue() == 14) {
            /*
             * Makes next player draw four cards and skips the next player
             */
            handleWildCard(playedCard);// NEEDS TO BE IMPLEMENTED-----------------------------------------------------------
        }

        deck.updateTopCard(playedCard);
    }

    // NEEDS TO BE IMPLEMENTED-----------------------------------------------------------
    public void handleWildCard(BaseCard playedCard) {
        /*
         * Get the color from the player using ui and change the color of the card using
         * setcolor method
         * 
         * Needs to be overriden for bot class
         */
    }
}
