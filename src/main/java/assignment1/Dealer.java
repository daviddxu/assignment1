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

	ArrayList<Card> deck = new ArrayList<Card>();
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
				deck.add(card);
			}
		}
		
		Collections.shuffle(deck); //shuffle generated cards	

	}
	
	public int deckSize() {	//for testing createDeck() only
		//return 0;
		
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
		System.out.println("There are " + deckSize() + " cards remaining in the deck.");
		
		return newCard;
	}
	
	public void stand() {	//when player wants to stand
		/*for(int i = 0; i < dealerCards.size(); i++) {
			if(dealerCards.get(i).getFaceUp() == false) {
				dealerCards.get(i).setFaceUp(true);
			}
		}*/
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
	}
	
	public int getDealerCardsSize() {
		//return 0;
		return dealerCards.size();
	}
	
	public void updateScore() {
		int newScore = 0;
		
		for(int i = 0; i < dealerCards.size(); i++) {
			newScore += dealerCards.get(i).getPoints();
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
	public void play() {
		
	}
}
