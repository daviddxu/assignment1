package assignment1;

/*
 * Card.java
 * Class for Card objects.
 * Note: While points corresponds to rank in most cases, the points for Ace are either 1 or 11 depending which one favours the player.
 * 
 */

public class Card {
	
	int rank;	//must be between 1-12 (11: Jack, 12: Queen, 13: King)
	int points;
	String suit;
	boolean faceup;
	public Card() {
		
	}

	public Card(int rank, int points, boolean faceup, String suit) {
		
		this.rank = rank;
		this.points = points;
		this.faceup = faceup;
		this.suit = suit;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		
		this.rank = rank;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public String getSuit() {
		
		return this.suit;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}
}
