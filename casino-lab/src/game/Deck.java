package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    ArrayList<Card> cards = new ArrayList<>(List.of(
            // clubs
            Face.ACE.of(Suit.CLUBS),
            Face.TWO.of(Suit.CLUBS),
            Face.THREE.of(Suit.CLUBS),
            Face.FOUR.of(Suit.CLUBS),
            Face.FIVE.of(Suit.CLUBS),
            Face.SIX.of(Suit.CLUBS),
            Face.SEVEN.of(Suit.CLUBS),
            Face.EIGHT.of(Suit.CLUBS),
            Face.NINE.of(Suit.CLUBS),
            Face.TEN.of(Suit.CLUBS),
            Face.JACK.of(Suit.CLUBS),
            Face.QUEEN.of(Suit.CLUBS),
            Face.KING.of(Suit.CLUBS),

            // spades
            Face.ACE.of(Suit.SPADES),
            Face.TWO.of(Suit.SPADES),
            Face.THREE.of(Suit.SPADES),
            Face.FOUR.of(Suit.SPADES),
            Face.FIVE.of(Suit.SPADES),
            Face.SIX.of(Suit.SPADES),
            Face.SEVEN.of(Suit.SPADES),
            Face.EIGHT.of(Suit.SPADES),
            Face.NINE.of(Suit.SPADES),
            Face.TEN.of(Suit.SPADES),
            Face.JACK.of(Suit.SPADES),
            Face.QUEEN.of(Suit.SPADES),
            Face.KING.of(Suit.SPADES),

            // hearts
            Face.ACE.of(Suit.HEARTS),
            Face.TWO.of(Suit.HEARTS),
            Face.THREE.of(Suit.HEARTS),
            Face.FOUR.of(Suit.HEARTS),
            Face.FIVE.of(Suit.HEARTS),
            Face.SIX.of(Suit.HEARTS),
            Face.SEVEN.of(Suit.HEARTS),
            Face.EIGHT.of(Suit.HEARTS),
            Face.NINE.of(Suit.HEARTS),
            Face.TEN.of(Suit.HEARTS),
            Face.JACK.of(Suit.HEARTS),
            Face.QUEEN.of(Suit.HEARTS),
            Face.KING.of(Suit.HEARTS),

            // diamonds
            Face.ACE.of(Suit.DIAMONDS),
            Face.TWO.of(Suit.DIAMONDS),
            Face.THREE.of(Suit.DIAMONDS),
            Face.FOUR.of(Suit.DIAMONDS),
            Face.FIVE.of(Suit.DIAMONDS),
            Face.SIX.of(Suit.DIAMONDS),
            Face.SEVEN.of(Suit.DIAMONDS),
            Face.EIGHT.of(Suit.DIAMONDS),
            Face.NINE.of(Suit.DIAMONDS),
            Face.TEN.of(Suit.DIAMONDS),
            Face.JACK.of(Suit.DIAMONDS),
            Face.QUEEN.of(Suit.DIAMONDS),
            Face.KING.of(Suit.DIAMONDS)
    ));

    public Deck() {}

    /** Shuffle the deck. */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /** Draw the next card from the deck (pop). */
    public Card draw() {
        return cards.removeLast();
    }

    /** Draw the next card without removing it. */
    public Card peek() {
        return cards.getLast();
    }
}
