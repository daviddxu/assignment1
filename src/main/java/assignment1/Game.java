package assignment1;

import java.util.Scanner;
import java.util.ArrayList;
/*
 * Game.java (BlackJack)
 * Contains main method for game operation
 * Dealer's face down card is excluded from score calculation
 */
public class Game {
	
	Dealer dealer = new Dealer();
	Player player = new Player();
	

	
	public void dealToPlayer() {
		
		player.playerCards.addAll(dealer.dealToPlayer());
		player.updateScore();
		
	}
	
	public void hit() {
		player.playerCards.add(dealer.hit());
		player.updateScore();
	
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
	
	public int blackJackTest() {	//return 0 if n/a, 1 if dealer wins and 2 if player wins; check dealer first
		
	/*	int aceFlag = 0;
		int faceFlag = 0;
		int tenFlag = 0;
	*/	
		for(int i = 0 ; i < dealer.dealerCards.size(); i++) {	//check if the player has an Ace and 10, 11, 12, 13
				if(dealer.dealerCards.get(i).getRank() == 1) {
			//		aceFlag = 1;
					for(int j = 0; j < dealer.dealerCards.size(); j++) {
						if(dealer.dealerCards.get(j).getRank() == 10 || dealer.dealerCards.get(i).getRank() == 11 || dealer.dealerCards.get(i).getRank() == 12 || dealer.dealerCards.get(i).getRank() == 13) {
							return 1;
						}
					}
				}
				
				else if(dealer.dealerCards.get(i).getRank() == 10 || dealer.dealerCards.get(i).getRank() == 11 || dealer.dealerCards.get(i).getRank() == 12 || dealer.dealerCards.get(i).getRank() == 13) {
		//			tenFlag = 1;
					
					for(int k = 0; k < dealer.dealerCards.size(); k++) {
						if(dealer.dealerCards.get(k).getRank() == 1) {
							return 1;
						}
					}
				}
		}
		
		
		for(int i = 0; i < player.playerCards.size(); i++) {
			if(player.playerCards.get(i).getRank() == 1) {
				for(int j = 0; j < player.playerCards.size(); j++) {
					if(player.playerCards.get(j).getRank()==10 || player.playerCards.get(j).getRank() == 11 || player.playerCards.get(j).getRank() == 12 || player.playerCards.get(j).getRank() == 13) {
						return 2;
					}
				}
			}
			
			else if(player.playerCards.get(i).getRank() == 10 || player.playerCards.get(i).getRank() == 11 || player.playerCards.get(i).getRank() == 12 || player.playerCards.get(i).getRank() == 13) {
					for(int k = 0; k < player.playerCards.size(); k++) {
						if(dealer.dealerCards.get(k).getRank()==1) {
							return 2;
						}
					}
			}
		}
		
		
		
		return 0;
	}
	
		public static void main (String [] args) {
			
			//System.out.println("Hello");
			
			Game game = new Game();
			Scanner input = new Scanner(System.in);
			int choice = 0;
			System.out.println("Press (1) for console input, (2) for file input");
			choice = input.nextInt();
			if(choice == 1) {	//console input
				
			}else if (choice == 2) {	//file input
				/*PLACEHOLDER*/
				System.out.println("NOT IMPLEMENTED");
			}
		}
}
