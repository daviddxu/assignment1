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

	ArrayList<Card> cards = new ArrayList<Card>();
	ArrayList<Card> dealerCards = new ArrayList<Card>();
	int score = 0;
	int [] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	String [] suits = {"S", "C", "D", "H"};	//S- Spades, C- Clubs, D- Diamonds, H- Hearts
	
	
	public Dealer() {
		
		this.score = 0;
		
	}
	
	//public Dealer()
	public void createDeck() {	//create a 52 card deck
		
		Card card;
		
		for(int i = 0; i < ranks.length; i++) {
			
			for(int j = 0; j < suits.length; j++) {
				card = new Card(ranks[i], ranks[i], true, suits[j]);
				cards.add(card);
			}
		}
		
		Collections.shuffle(cards); //shuffle generated cards	

	}
	
	public int deckSize() {	//for testing createDeck() only
		//return 0;
		
		return cards.size();
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void play() {
		
	}
}
