package com.lucasseeley.shuffledeck;

public class Card {
    String Face;
    String Suit;

    public Card(String face, String suit) {
        Face = face;
        Suit = suit;
    }

    // Overrides the toString method in order to display the cards properly
    public String toString(String suit) {
        // For a standard deck
        if (suit.equals("Spades") || suit.equals("Clubs") || suit.equals("Hearts") || suit.equals("Diamonds")) {
            return Face + " of " + Suit;
        } // For an UNO deck
        else {
            return Suit + " " + Face;
        }
    }
}
