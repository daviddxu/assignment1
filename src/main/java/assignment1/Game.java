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
			if(dealer.dealerCards.get(i).getRank().compareTo("A")==0) {
				if(dealer.getScore() + 11 <= 21) {	//if dealer score is favoured by Ace being worth 11  
					dealer.dealerCards.get(i).setPoints(11);
					return true;
				}
			}
		}
		
		for(int j = 0; j < player.playerCards.size(); j++) {
			if(player.playerCards.get(j).getRank().compareTo("A") == 0) {
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
				if(dealer.dealerCards.get(i).getRank().compareTo("A")==0) {
			//		aceFlag = 1;
					for(int j = 0; j < dealer.dealerCards.size(); j++) {
						if(dealer.dealerCards.get(j).getRank().compareTo("10") == 0 || dealer.dealerCards.get(j).getRank().compareTo("J") == 0 || dealer.dealerCards.get(j).getRank().compareTo("Q") == 0  || dealer.dealerCards.get(j).getRank().compareTo("K") == 0 ) {
							return 1;
						}
					}
				}
				
				else if(dealer.dealerCards.get(i).getRank().compareTo("10") == 0 || dealer.dealerCards.get(i).getRank().compareTo("J") == 0 || dealer.dealerCards.get(i).getRank().compareTo("Q") == 0  || dealer.dealerCards.get(i).getRank().compareTo("K") == 0 ) {
		//			tenFlag = 1;
					
					for(int k = 0; k < dealer.dealerCards.size(); k++) {
						if(dealer.dealerCards.get(k).getRank().compareTo("A")==0) {
							return 1;
						}
					}
				}
		}
		
		
		for(int i = 0; i < player.playerCards.size(); i++) {
			if(player.playerCards.get(i).getRank().compareTo("A")==0) {
				for(int j = 0; j < player.playerCards.size(); j++) {
					if(player.playerCards.get(j).getRank().compareTo("10")==0|| player.playerCards.get(j).getRank().compareTo("J")==0 || player.playerCards.get(j).getRank().compareTo("Q")==0 ||  player.playerCards.get(j).getRank().compareTo("K")==0 ) {
						return 2;
					}
				}
			}
			
			else if(player.playerCards.get(i).getRank().compareTo("10")==0|| player.playerCards.get(i).getRank().compareTo("J")==0 || player.playerCards.get(i).getRank().compareTo("Q")==0 ||  player.playerCards.get(i).getRank().compareTo("K")==0 ) {
					for(int k = 0; k < player.playerCards.size(); k++) {
						if(dealer.dealerCards.get(k).getRank().compareTo("A")==0) {
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
				
				game.dealer.createDeck();
				game.dealToPlayer();
				game.dealer.dealToSelf();
				
				System.out.println("Player's cards: " );
				for(int i = 0; i < game.getPlayerCardsSize(); i++) {
					System.out.print(game.player.playerCards.get(i).getRank() + "" + game.player.playerCards.get(i).getSuit() + " ");
				}
				System.out.println("");
				System.out.println("Dealer's cards: ");
				for(int i = 0; i < game.dealer.dealerCards.size(); i++) {
					if(game.dealer.dealerCards.get(i).getFaceUp() == true) {
					System.out.println(game.dealer.dealerCards.get(i).getRank() + "" + game.dealer.dealerCards.get(i).getSuit());
					}
				}
				
				String play = null;
								
				
				
			}else if (choice == 2) {	//file input
				/*PLACEHOLDER*/
				System.out.println("NOT IMPLEMENTED");
			}
		}
}
