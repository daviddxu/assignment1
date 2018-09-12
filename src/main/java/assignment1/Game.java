package assignment1;

import java.util.ArrayList;
/*
 * Game.java (BlackJack)
 * Contains main method for game operation
 * 
 */
public class Game {

	
	//ArrayList<Card> dealerPlay = new ArrayList<Card>();
	//ArrayList<Card> player = new ArrayList<Card>();
	Dealer dealer = new Dealer();
	Player player = new Player();
	
	public int bustCheck() {
		
		int flag = 0; //return 1 for player bust, 2 for dealer bust
		
		
		
		return flag;
	}
	
	public boolean aceCheck() {	//check if player and dealer will bust if an ace's points are set to 11; called if player or dealer is dealt an Ace or if the dealer flips up an Ace
		
		int dealerScoreTemp = dealer.getScore();
		int playerScoreTemp = player.getScore();
		for(int i = 0; i < dealer.dealerCards.size(); i++) {
			if(dealer.dealerCards.get(i).getRank() == 1) {
				
			}
		}
		
		return false;
		
	}
		public static void main (String [] args) {
			
			System.out.println("Hello");
		}
}
