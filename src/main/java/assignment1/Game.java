package assignment1;

import java.util.ArrayList;
/*
 * Game.java (BlackJack)
 * Contains main method for game operation
 * Dealer's face down card is excluded from score calculation
 */
public class Game {

	
	//ArrayList<Card> dealerPlay = new ArrayList<Card>();
	//ArrayList<Card> player = new ArrayList<Card>();
	
	Dealer dealer = new Dealer();
	Player player = new Player();
	

	
	public void dealToPlayer() {
		
		player.playerCards.addAll(dealer.dealToPlayer());
		player.updateScore();
		//System.out.println("player's score: " + player.getScore());
		
	}
	
	public void hit() {
		player.playerCards.add(dealer.hit());
		player.updateScore();
	//	bustCheck();
	}
	
	public void dealerHit() {
		dealer.flipCard();
		aceCheck();
		dealer.dealerCards.add(dealer.hit());
		dealer.updateScore();
	}
	public void stand() {
		dealer.stand();
		aceCheck();
		
	}
	
	public int getPlayerCardsSize() {	//for testing dealToPlayer operation only
		return player.playerCards.size();
	}
	/*public int bustCheck() {
		
		int flag = 0; //return 1 for player bust, 2 for dealer bust
		
		
		
		return flag;
	}*/
	
	public boolean bustCheck() {	//check if player or dealer (in that order) has busted and return accordingly
		
		if(player.getScore() > 21) {
			return true;
		}
		
		if(dealer.getScore() > 21) {
			return true;
		}
		return false;
	}
	
	public boolean aceCheck() {	//check if player and dealer will bust if an ace's points are set to 11; called if player or dealer is dealt an Ace or if the dealer flips up an Ace
		
		//int dealerScoreTemp = dealer.getScore();
		//int playerScoreTemp = player.getScore();
		for(int i = 0; i < dealer.dealerCards.size(); i++) {
			if(dealer.dealerCards.get(i).getRank() == 1) {
				if(dealer.getScore() + 11 <= 21) {	//if dealer score is favoured by Ace being worth 11  
					dealer.dealerCards.get(i).setPoints(11);
					return true;
				}
			}
		}
		
		for(int j = 0; j < player.playerCards.size(); j++) {
			if(player.playerCards.get(j).getRank() == 1) {
				if(player.getScore() + 11 <= 21) {	//if dealer score is favoured by Ace being worth 11  
					player.playerCards.get(j).setPoints(11);
					return true;
				}
			}
		}
		
		return false;
		
	}
		public static void main (String [] args) {
			
			System.out.println("Hello");
		}
}
