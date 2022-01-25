package com.lucasseeley.shuffledeck;

import java.util.*;

// This program will allow a user to either grab a deck of Uno cards, or a standard deck of cards
public class Main {
    public static void main(String[] args) {
        Card[] cards = Briefing();
        for(Card card : cards){
            System.out.println(card.toString(card.Suit));
        }

        Card[] shuffledCards = ShuffleCards(cards);
        for (Card card : shuffledCards) {
            System.out.println(card.toString(card.Suit));
        }
    }

    // Welcoming method that asks the user for the type of deck and number of decks needed
    public static Card[] Briefing() {
        // Declarations
        String userChosenDeck = "";
        int numOfDecks = 1;
        boolean isValid = false;
        Scanner readInput = new Scanner(System.in);

        // The first goal is finding out which type of deck the user would like
        System.out.println("Hello, welcome to my card game!");
        System.out.println("Would you like to use a standard deck of cards or an uno deck?(standard/UNO)");

        while (!userChosenDeck.equals("uno") && !userChosenDeck.equals("standard")) {
            userChosenDeck = readInput.nextLine().toLowerCase();

            // In case the user fails to enter a correct entry this statement will execute
            if (!userChosenDeck.equals("uno") && !userChosenDeck.equals("standard")) {
                System.out.println("Please enter the a valid input of \"standard\" or \"UNO.\"");
            }
        }

        // Next we will find out how many decks the user would like
        System.out.println("How many decks of " + userChosenDeck + " deck cards would you like?");
        // Here we have a loop to request the number of decks from the user until the user enters a valid number
        while (!isValid) {
            try {
                numOfDecks = Integer.parseInt(readInput.nextLine());
                isValid = true;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
            }
        }

        return GetCards(userChosenDeck, numOfDecks);
    }

    // Generates a non-shuffled deck of cards
    public static Card[] GetCards(String cardType, int decks) {
        // Declarations
        String[] suits = {};
        String[] faces = {};
        int cardsInDeck = 0;
        int cards = 0;
        int index = 0;

        // Defaulted for standard deck is modified in else if statement for uno
        int facesFirstEnd = 0;
        int maxFacesIndex = 13;
        int postMaxFacesIndex = 13;

        if (cardType.equals("standard")) {
            String[] standardSuits = {"Spades", "Clubs", "Hearts", "Diamonds"};
            String[] standardFaces = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

            // Setters
            suits = standardSuits;
            faces = standardFaces;
            cards = 52;
            cardsInDeck = 52;

        } else if (cardType.equals("uno")) {
            String[] unoSuits = {"Red", "Blue", "Green", "Yellow", "Wild"};
            String[] unoFaces = {"Zero", "One", "One", "Two", "Two", "Three", "Three", "Four", "Four", "Five", "Five", "Six", "Six", "Seven", "Seven", "Eight", "Eight", "Nine", "Nine", "Draw Two", "Draw Two", "Skip", "Skip", "Reverse", "Reverse", "Card", "Card", "Card", "Card", "Draw Four", "Draw Four", "Draw Four", "Draw Four"};

            // Setters
            suits = unoSuits;
            faces = unoFaces;
            cards = 108;
            cardsInDeck = 108;
            facesFirstEnd = 8;
            maxFacesIndex = 8;
            postMaxFacesIndex = 8;
        }

        Card[] deckOfCards = new Card[cardsInDeck * decks];
        System.out.println("Generating the deck of cards...");

        while (decks > 0) {
            // Continuously loop through the arrays to add each and every card
            // j = facesIndex k = suitsIndex
            for (int i = index, j = 1, k = 1; i < cardsInDeck; i++, j++) {

                /* This statement is creating a new card starting at the end of each array, the purpose of this is due
                to the uno deck having the wild cards */
                Card card = new Card(faces[faces.length - j], suits[suits.length - k]);
                deckOfCards[i] = card;

                /* Once the facesIndex reaches its max value it can reach it will reset the facesIndex to its specified
                value. The purpose of this reassignment is due to the wild cards in the uno deck where the program
                needs to ensure the wild suit is matched with its designated face */
                if (j == maxFacesIndex) {
                    j = facesFirstEnd;
                    k++;
                    maxFacesIndex = faces.length;
                }
            }

            // Resets the values to where they should be positioned for the next deck
            decks--;
            index += cards;
            cardsInDeck += cards;
            maxFacesIndex = postMaxFacesIndex;
        }

        return deckOfCards;
    }

    // Shuffles a deck of cards
    public static Card[] ShuffleCards(Card[] cards) {
        // Declarations
        ArrayList<Card> deckOfCards = new ArrayList<>(Arrays.asList(cards));
        Card[] shuffledCards = new Card[cards.length];
        Random random = new Random();

        System.out.println("Shuffling the cards...");

        // Loops until the non-shuffled deck is empty randomly selecting a card to insert into the shuffledCards array
        for (int i = 0; deckOfCards.size() != 0; i++) {
            int index = random.nextInt(deckOfCards.size());

            shuffledCards[i] = deckOfCards.get(index);
            deckOfCards.remove(index);
        }

        return shuffledCards;
    }
}