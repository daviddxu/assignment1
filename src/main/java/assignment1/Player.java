package assignment1;

import java.util.ArrayList;

/*
 * Player.java
 * Player class
 *
 * 
 * 
 */
public class Player {

	ArrayList<Card> playerCards = new ArrayList<Card>();
	//ArrayList<Card> playerHand = new ArrayList<Card>();
	int score = 0;
	
	public Player() {
		this.score = 0;
	}
	
	
	public int getScore() {
		return this.score;
	}
	
	public void updateScore() {
		
		int newScore = score;
		for(int i = 0; i < playerCards.size(); i++) {
			newScore += playerCards.get(i).getPoints();
		}
		this.score = newScore;
	}
	public boolean aceCheck() {	//return true if an Ace's points are changed to 11, false otherwise
		
		return false;
	}
	

}
