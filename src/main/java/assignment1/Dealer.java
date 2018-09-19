package assignment1;

import java.util.Random;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Dealer.java
 * Dealer deals cards to player and also plays
 * Face down cards are excluded from score calculation until flipped.
 * 
 */
public class Dealer {

	ArrayList<Card> deck = new ArrayList<Card>();	//deck of cards for game
	ArrayList<Card> uDeck = new ArrayList<Card>();	//unshuffled deck for testing purposes only
	ArrayList<Card> dealerCards = new ArrayList<Card>();	//dealer's hand
	int score = 0;
//	int [] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	String [] ranks =  {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	String [] suits = {"S", "C", "D", "H"};	//S- Spades, C- Clubs, D- Diamonds, H- Hearts
 
	
	public Dealer() {		
		this.score = 0;
	}
	
	public void createDeck() {	//create a 52 card deck
		
		Card card;
		int points = 0;
		for(int i = 0; i < ranks.length; i++) {			
			for(int j = 0; j < suits.length; j++) {
				points = genPoints(ranks[i]);
				card = new Card(ranks[i], points  , true, suits[j]);
				deck.add(card);
			}
		}		
		Collections.shuffle(deck); //shuffle generated cards	
	}
	
	public void createUnshuffledDeck() {	//create an unshuffled 52 card deck *FOR TESTING PURPOSES ONLY*
		
		Card card;
		int points = 0;
		for(int i = 0; i <ranks.length; i++) {
			for(int j = 0; j<suits.length; j++) {
				points = genPoints(ranks[i]);
				card = new Card(ranks[i], points, true, suits[j]);
				uDeck.add(card);
			}
		}
	}
	
	public boolean equalDeck() {	//check if two decks are equal	*FOR TESTING PURPOSES ONLY*
		
		createDeck();
		createUnshuffledDeck();		
		int flag = 0;
		if(deck.size()!=uDeck.size()) {
			return false;
		}
		
		for(int i = 0; i < deck.size(); i++) {
			if(deck.get(i).getPoints() == uDeck.get(i).getPoints() && deck.get(i).getRank().equals(uDeck.get(i).getRank()) &&deck.get(i).getSuit().equals(uDeck.get(i).getSuit()) && deck.get(i).getFaceUp() == uDeck.get(i).getFaceUp()) {
				flag++;
			}
		}
				
		if(flag == deck.size()) {	//if all cards in both decks are in the same order (shuffled deck was not actually shuffled), return true
			return true;
		}
		return false;
	}
	
	
	public int deckSize() {	//for testing createDeck() only
		return deck.size();
	}
	
	public int getScore() {
		return this.score;
	}

	public ArrayList<Card> dealToPlayer() {	//deal two cards to the player; this function is only called at the start of a game
		
		ArrayList<Card> twoCards = new ArrayList<Card>();				
		twoCards.add(deck.get(0));
		deck.remove(0);
		twoCards.add(deck.get(0));
		deck.remove(0);
		return twoCards;
		
	}
	
	public Card hit() {	//when player or dealer wants to hit
		Card newCard = deck.get(0);
		deck.remove(0);
		//System.out.println("There are " + deckSize() + " cards remaining in the deck.");
		
		return newCard;
	}
	
	public void stand() {	//when a player wants to stand
		flipCard();
	}
	
	public void dealToSelf() {
		dealerCards.add(deck.get(0));
		deck.remove(0);
		deck.get(0).setFaceUp(false);
		dealerCards.add(deck.get(0));
		deck.remove(0);
	}
	
	public void flipCard() {	//flip dealer's face down card
		for(int i = 0; i < dealerCards.size(); i++) {
			if(dealerCards.get(i).getFaceUp() == false) {
				dealerCards.get(i).setFaceUp(true);
			}
		}
		updateScore();
	}
	
	public int getDealerCardsSize() {
		
		return dealerCards.size();
	}
	
	public void updateScore() {
	int newScore = 0;
		
		for(int i = 0; i < dealerCards.size(); i++) {			
		//	if(dealerCards.get(i).getFaceUp() == true) {
				newScore += dealerCards.get(i).getPoints();
			//}
		}
		this.score = newScore;
	}
	
	public boolean standTest() {
				
		for(int i = 0; i < dealerCards.size(); i++) {
			if(dealerCards.get(i).getFaceUp() == false) {
				return false;
			}
		}
		return true;
	}
	
	public int genPoints(String rank) {	//convert ranks (string) to points (int)
				
		if(rank.compareTo("A") == 0) {
			return 1;
		}
		else if(rank.compareTo("2")==0) {
			return 2;
		}
		if(rank.compareTo("3") == 0) {
			return 3;
		}
		else if(rank.compareTo("4")==0) {
			return 4;
		}
		if(rank.compareTo("5") == 0) {
			return 5;
		}
		else if(rank.compareTo("6")==0) {
			return 6;
		}
		if(rank.compareTo("7") == 0) {
			return 7;
		}
		else if(rank.compareTo("8")==0) {
			return 8;
		}
		if(rank.compareTo("9") == 0) {
			return 9;
		}
		else if(rank.compareTo("10")==0) {
			return 10;
		}
		if(rank.compareTo("J") == 0) {
			return 10;
		}
		else if(rank.compareTo("Q")==0) {
			return 10;
		}
		if(rank.compareTo("K") == 0) {
			return 10;
		}else {
			return -1;	//failure case
		}
		 		
	}
	
	public Card cardGen(String cardStr) {	//must take 2 character String
		
		Card newCard = new Card();
		
		String rank = null;
		String suit = null;
		boolean faceUp = true;
		int points = -1;
				
		if(cardStr.length() != 2) {
			
			if(cardStr.contains("10")) {
				if(cardStr.charAt(0) == 'S') {
					rank = "10";
					points = 10;
					suit = "S";
					newCard = new Card(rank, points, faceUp, suit);									
					return newCard;
				}
				else if(cardStr.charAt(0) == 'D') {
					rank = "10";
					points = 10;
					suit = "D";
					newCard = new Card(rank, points, faceUp, suit);
					return newCard;
				}
				else if (cardStr.charAt(0) == 'C') {
					rank = "10";
					points = 10;
					suit = "C";
					newCard = new Card(rank, points, faceUp, suit);
					return newCard;
				}
				else if(cardStr.charAt(0) == 'H') {
					rank = "10";
					points = 10;
					suit = "H"; 	
					newCard = new Card(rank, points, faceUp, suit);
					return newCard;
				}else {
					return null;
				}
			}else {
				return null;
			}
		}
		
		
		if(cardStr.charAt(0) == 'S') {
			suit = "S";
		}else if (cardStr.charAt(0) == 'C') {
			suit = "C";
		}else if(cardStr.charAt(0) == 'D') {
			suit = "D";
		}else if(cardStr.charAt(0) == 'H') {
			suit = "H";
		}

		
		if(cardStr.charAt(1) == 'A') {
			rank = "A";
			points = 1;
		}
		else if(cardStr.charAt(1) == 'K') {
			rank = "K";
			points = 10;
		}else if (cardStr.charAt(1) == 'Q') {
			rank = "Q";
			points = 10;
		}else if(cardStr.charAt(1) == 'J') {
			rank = "J";
			points = 10;
		}else if(cardStr.charAt(1) == '9') {
			rank = "9";
			points = 9;
		}else if (cardStr.charAt(1) == '8') {
			rank = "8";
			points = 8;
		}else if (cardStr.charAt(1) == '7') {
			rank = "7";
			points = 7;
		}else if(cardStr.charAt(1) == '6') {
			rank = "6";
			points =6;
		}else if(cardStr.charAt(1) == '5') {
			rank = "5";
			points = 5;
		}else if(cardStr.charAt(1) == '4') {
			rank = "4";
			points = 4;
		}else if(cardStr.charAt(1) == '3') {
			rank = "3";
			points = 3;
		}else if(cardStr.charAt(1) == '2') {
			rank = "2";
			points = 2;
		}else {
			return null;
		}
		
		newCard = new Card(rank, points, faceUp, suit);
		return newCard;
		
		
	}
	public void play() {	//DEPRECATED
		
	}
}
