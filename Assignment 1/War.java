//John McQuown CS 0445
//January 22, 2015

import java.util.*;
public class War {
	private static MultiDS<Card> initialDeck = new MultiDS<Card>(52);
	private static MultiDS<Card> player1Deck = new MultiDS<Card>(52);
	private static MultiDS<Card> player2Deck = new MultiDS<Card>(52);
	private static MultiDS<Card> player1Discard = new MultiDS<Card>(52);
	private static MultiDS<Card> player2Discard = new MultiDS<Card>(52);
	private static MultiDS<Card> warPile = new MultiDS<Card>(20);
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Game of War!");
		System.out.println("\nNow dealing cards to the players");
		
		loadDeck();
		System.out.println("\nPlayer 1's Deck:");
		System.out.println(player1Deck.toString());
		System.out.println("\nPlayer 2's Deck:");
		System.out.println(player2Deck.toString());
		
		
		System.out.println("\nStarting the WAR!");
		int numOfRounds = Integer.parseInt(args[0]);
		playWar(numOfRounds);
		
		//If the max amount of rounds pass without a winner then the size of the decks are compared
		System.out.println("After " + numOfRounds + " rounds here are the results: ");
		//But first cards are loaded into their respective decks from their discard piles
		while (!player1Discard.empty())
			player1Deck.addItem(player1Discard.removeItem());
		while (!player2Discard.empty())
			player2Deck.addItem(player2Discard.removeItem());
		System.out.println("Player 1: " + player1Deck.size() + " cards");
		System.out.println("Player 2: " + player2Deck.size() + " cards");
		if (player1Deck.size() > player2Deck.size())
			System.out.println("Player 1 Wins!");
		else if (player2Deck.size() > player1Deck.size())
			System.out.println("Player 2 Wins!");
		else
			System.out.println("It is a stalemate!");
	}
	//Method that loads all 52 cards into a deck, then the deck is shuffled
	//Half the cards are added to one deck and the remaining 26 to the other
	public static void loadDeck() {
		
		for (Card.Ranks r: Card.Ranks.values())
			for (Card.Suits s: Card.Suits.values())
				initialDeck.addItem(new Card(s, r));
		initialDeck.shuffle();
		while (!initialDeck.empty()) {
			player1Deck.addItem(initialDeck.removeItem());
			player2Deck.addItem(initialDeck.removeItem());
		}	
	}
//	The method that actually plays the game, with a parameter and a for loop for the number of rounds
	public static void playWar(int rounds) {
		for (int i = 0; i <= rounds; i++) {
//			If the deck for player 1 is empty, shuffle the discard pile and add them to the deck
			if (player1Deck.empty()) {
				//If both the deck and discard pile are empty the other player wins and the game is terminated
				if (player1Deck.empty() && player1Discard.empty()) {
					System.out.println("\nPlayer 1 is out of cards!");
					System.out.println("Player 2 Wins!!");
					System.exit(0);
				}
				//While loop removes all items from the discard pile and adds them to the deck
				System.out.println("Getting and shuffling the pile for Player 1");
				player1Discard.shuffle();
				while (!player1Discard.empty())
					player1Deck.addItem(player1Discard.removeItem());
			}
			if (player2Deck.empty()) {
				if (player2Deck.empty() && player2Discard.empty()) {
					System.out.println("\nPlayer 2 is out of cards!");
					System.out.println("Player 1 Wins!!");
					System.exit(0);;
				}
				System.out.println("Getting and shuffling the pile for Player 2");
				player2Discard.shuffle();
				while (!player2Discard.empty())
					player2Deck.addItem(player2Discard.removeItem());
			}
//			If at any point a player has a full deck then they win
			if (player1Deck.full()) {
				System.out.println("Player 1 has won the game by having the entire deck of cards!");
				System.exit(0);
			}
			else if(player2Deck.full()) {
				System.out.println("Player 1 has won the game by having the entire deck of cards!");
				System.exit(0);
			}
			compareCards(player1Deck.removeItem(), player2Deck.removeItem());
		}
			
	}
	public static void compareCards(Card x, Card y)
	{
		int result = x.compareTo(y);
		if (result > 0) {
			System.out.println("Player 1 Wins: " + x + " beats " + y);
			player1Discard.addItem(y);
			player1Discard.addItem(x);
			while (!warPile.empty())
				player1Discard.addItem(warPile.removeItem());
		}
		else if (result < 0) {
			System.out.println("Player 2 Wins: " + x + " loses to " + y);
			player2Discard.addItem(x);
			player2Discard.addItem(y);
			while (!warPile.empty())
				player2Discard.addItem(warPile.removeItem());
		}
		else {
			System.out.println("WAR: " + x + " ties " + y);
			warPile.addItem(x);
			warPile.addItem(y);
		}
//		if (x.equals(y)) {
//			System.out.println(x + " and " + y + " are equal ");
//		}
//		else {
//			System.out.println(x + " and " + y + " are not equal ");
//		}
	}
}

