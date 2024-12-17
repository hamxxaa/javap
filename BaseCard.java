public class BaseCard {
    private String color;
    private int value;
    
    public BaseCard(String color, int value) {
        this.color = color;
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return (this.color + " " + this.value);
    }
}