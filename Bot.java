public class Bot extends Player {
    private String name;

    // Constructor to initialize bot with deck, moderator, and name
    public Bot(Deck deck, Moderator mod, String name) {
        super(deck, mod);
        this.name = name;
    }

    /*
     * Method to choose a card to play from the bot's hand
     * 
     * @return The card that was played. Null if no card is playable
     */
    public BaseCard playCard(BaseCard topCard) {
        WeightedCollection<BaseCard> playableCards = new WeightedCollection<>();
        for (BaseCard card : hand) {
            if (card.isSameColor(topCard)) {
                if (card.isSpecialCard()) {
                    playableCards.add(10, card);
                } else {
                    playableCards.add(5, card);
                }
            } else if (card.isSameValue(topCard)) {
                playableCards.add(3, card);
            } else if (card.isWildCard()) {
                playableCards.add(2, card);
            } else if (card.isWildDrawFourCard()) {
                playableCards.add(1, card);
            }
        }
        if (playableCards.isEmpty()) {
            System.out.println(this.name + " drew a card");
            return null;
        } else {
            BaseCard card = playableCards.next();
            hand.remove(card);
            System.out.println(this.name + " played " + card);
            return card;
        }
    }

    /*
     * Override method to handle the played card
     * 
     * @param card The card that was played
     */
    @Override
    public void handleWildCard(BaseCard card) {
        String chosenColor = chooseMostFrequentColor();
        card.setColor(chosenColor);
        System.out.println("Wild card played. Color changed to: " + chosenColor);
    }

    /*
     * Method to choose the most frequent color in the bot's hand
     * 
     * @return The most frequent color in the bot's hand
     */
    private String chooseMostFrequentColor() {
        int[] colorCount = new int[4];

        for (BaseCard c : hand) {
            switch (c.getColor()) {
                case "Red":
                    colorCount[0]++;
                    break;
                case "Green":
                    colorCount[1]++;
                    break;
                case "Blue":
                    colorCount[2]++;
                    break;
                case "Yellow":
                    colorCount[3]++;
                    break;
            }
        }

        int maxCountIndex = 0;
        for (int i = 1; i < colorCount.length; i++) {
            if (colorCount[i] > colorCount[maxCountIndex]) {
                maxCountIndex = i;
            }
        }

        return deck.colors[maxCountIndex];
    }

    // Override doMove method to perform a move for the bot
    @Override
    public void doMove() {
        System.out.println(this.name + "'s turn" + " (Has " + hand.size() + " cards)");
        if (!blocked) {
            BaseCard playedCard = playCard(deck.topCard);
            if (playedCard == null) {
                drawCard();
                mod.setCurrentPlayer(mod.getNextPlayer(this));
            } else {
                handleCards(playedCard);
            }

        } else {
            blocked = false;
            System.out.println(this.name + " is blocked");
            mod.setCurrentPlayer(mod.getNextPlayer(this));
        }
    }

    // Override toString method to provide a string representation of the bot
    @Override
    public String toString() {
        return this.name;
    }

    /*
     * Override method to stack draw cards
     * 
     * @param topCard The top card in the played deck
     * 
     * @param stack The number of cards to draw
     */
    @Override
    public void chainDraw(BaseCard topCard, int stack) {
        for (BaseCard card : hand) {
            if (card.isSameValue(topCard)) {
                if (card.getValue() == 14) {
                    handleWildCard(card);
                }
                hand.remove(card);
                stack = stack + ((card.getValue() == 14) ? 4 : 2);
                deck.updateTopCard(card);
                System.out.println(this.name + " played " + card + " and stacked to " + stack);
                mod.getNextPlayer(this).chainDraw(card, stack);
                return;
            }
        }
        for (int i = 0; i < stack; i++) {
            drawCard();
        }
        mod.setCurrentPlayer(mod.getNextPlayer(this));
        System.out.println(this + " drew " + stack + " cards");

    }
}