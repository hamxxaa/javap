public class BaseCard {
    private String color;
    private int value;

    // Constructor to initialize card with color and value
    public BaseCard(String color, int value) {
        this.color = color;
        this.value = value;
    }

    // Getter for color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Getter for value
    public int getValue() {
        return value;
    }

    // Override toString method to provide a string representation of the card
    @Override
    public String toString() {
        if (value == 10) {
            return (this.color + " " + "Block");
        } else if (value == 11) {
            return (this.color + " " + "Reverse");
        } else if (value == 12) {
            return (this.color + " " + "Draw Two");
        } else if (value == 13) {
            return (this.color + " " + "Wild");
        } else if (value == 14) {
            return (this.color + " " + "Wild Draw Four");
        }
        return (this.color + " " + this.value);
    }
}