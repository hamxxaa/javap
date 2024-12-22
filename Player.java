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
        if (isCardPlayable(hand.get(index), deck.topCard)) {
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
    public boolean isCardPlayable(BaseCard cardToPlay, BaseCard topCard) {
        return cardToPlay.isSameColor(topCard)
                || cardToPlay.isSameValue(topCard)
                || cardToPlay.isWildCard()
                || cardToPlay.isWildDrawFourCard();
    }

    // Method to get the size of the hand
    public int getHandSize() {
        return hand.size();
    }

    // Method to get a move from player and play the card
    public void doMove() {
        int move;
        while (true) {
            if (!blocked) {
                move = this.ui.getMove();
                if (move == 0) {
                    drawCard();
                    mod.setCurrentPlayer(mod.getNextPlayer(this));
                    break;
                } else {
                    BaseCard playedCard = playCard(move - 1);
                    if (playedCard == null) {
                        System.out.println("This card is not playable");
                    } else {
                        handleCards(playedCard);
                        break;
                    }
                }
            } else {
                blocked = false;
                System.out.println("Player is blocked");
                mod.setCurrentPlayer(mod.getNextPlayer(this));
                break;
            }
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
        deck.updateTopCard(playedCard);
        if (playedCard.getValue() == 10) {
            mod.blockPlayer(mod.getNextPlayer(this));
            mod.setCurrentPlayer(mod.getNextPlayer(this));

        } else if (playedCard.getValue() == 11) {
            mod.queue.reverse();
            mod.setCurrentPlayer(mod.getNextPlayer(this));

        } else if (playedCard.getValue() == 12) {
            mod.getNextPlayer(this).chainDraw(playedCard, 2);

        } else if (playedCard.getValue() == 13) {
            handleWildCard(playedCard);
            mod.setCurrentPlayer(mod.getNextPlayer(this));

        } else if (playedCard.getValue() == 14) {
            handleWildCard(playedCard);
            mod.getNextPlayer(this).chainDraw(playedCard, 4);
        }
    }

    /*
     * Method to handle wild card
     * 
     * @param playedCard The wild card that was played
     * 
     */
    public void handleWildCard(BaseCard playedCard) {

        String chosenColor = ui.getPlayerColorChoice();
        playedCard.setColor(chosenColor);
        System.out.println("Wild card played. Color changed to: " + chosenColor);
    }

    /*
     * Method to stack draw cards
     * @param topCard The top card in the played deck
     * @param stack The number of cards to draw
     */
    public void chainDraw(BaseCard topCard, int stack) {
        System.out.println("Bot played " + topCard + " you have to play a draw "
                + (topCard.getValue() == 14 ? "four" : "two") + " card or draw " + stack + " cards");
        int move = this.ui.getMove();
        if (move != 0 && hand.get(move - 1).isSameValue(topCard)) {
            if(hand.get(move - 1).getValue() == 14){
                handleWildCard(hand.get(move - 1));
            }
            hand.remove(move - 1);
            stack = stack + ((hand.get(move - 1).getValue() == 14) ? 4 : 2);
            deck.updateTopCard(hand.get(move - 1));
            System.out.println("Player played " + hand.get(move - 1) + " and stacked to " + stack);
            mod.getNextPlayer(this).chainDraw(hand.get(move - 1), stack);
        } else {
            for (int i = 0; i < stack; i++) {
                drawCard();
            }
            mod.setCurrentPlayer(mod.getNextPlayer(this));
            System.out.println("Player drew " + stack + " cards");
        }
    }
}
