package game;

public class Card {
    public Face face;
    public Suit suit;

    public Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", face, suit);
    }
}
