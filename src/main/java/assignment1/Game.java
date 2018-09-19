package assignment1;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
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
		aceCheck();
		player.updateScore();
		
	}
	
	public void dealToSelf() {
		dealer.dealToSelf();
		aceCheck();
		dealer.updateScore();
	}
	public int winCheck() {	//check dealer then player for win conditions	1 for dealer win, 2 for player win
		
		if(dealer.getScore() == 21) {
			return 1;
		}else if(blackJackTest()==1) {
			//System.out.println("Dealer has a blackjack");
			return 1;
		}else if(player.getScore() > 21) {
			System.out.println("Player has busted with a score of " + player.getScore());
			return 1;
		}else if(player.getScore() == 21) {
			return 2;
		}
		else if(blackJackTest()==2) {
			//System.out.println("Player has a blackjack");
			return 2;
		}else if (dealer.getScore() >21 ) {
			System.out.println("Dealer has busted with a score of " + dealer.getScore());
			return 2;
		}
		
		return 0;
	}
	public void hit() {
		player.playerCards.add(dealer.hit());
		player.updateScore();	
	}
	
	public void dealerHit() {	//dealer's hit
		dealer.flipCard();
		aceCheck();
		dealer.dealerCards.add(dealer.hit());
		dealer.updateScore();
	}
	
	public void dealerPlay() {
		if(softCheck() == true || dealer.getScore() <= 16) {	//if soft 17 or dealer points <= 16
			System.out.println("Dealer hits");
			dealerHit();
		}else {
			System.out.println("Dealer stands");
			stand();
		}
	}
	
	public boolean softCheck() {	//check for soft 17 (where 11 points come from an Ace)
		if(dealer.getScore() == 17) {
			for(int i = 0; i < dealer.getDealerCardsSize(); i++) {
				if(dealer.dealerCards.get(i).getRank().compareTo("A") == 0) {
					if(dealer.dealerCards.get(i).getPoints() == 11) {
						return true;
					}
				}
			}
		}
		return false;
	}
	 
	public void stand() {		//player's ('s') or dealer's stand (automatic)
		dealer.stand();
		aceCheck();	
		dealer.updateScore();
		player.updateScore();
	}
	
	public int getPlayerCardsSize() {	//for testing dealToPlayer operation only
		return player.playerCards.size();
	}

	
	public int bustCheck() {	//check if player or dealer (in that order) has busted and return accordingly
		
		if(player.getScore() > 21) {
			return 1;
		}
		
		if(dealer.getScore() > 21) {
			return 2;
		}
		return 0;
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
						if(player.playerCards.get(k).getRank().compareTo("A")==0) {
							return 2;
						}
					}
			}
		}
				
		return 0;
	}
	
		public static void main (String [] args) {
			
			
			Game game = new Game();
			Scanner input = new Scanner(System.in);
			int choice = 0;
			System.out.println("Press (1) for console input, (2) for file input");
			choice = input.nextInt();
			String play = null;								
			int win = 0;
			if(choice == 1) {	//console input
				
				game.dealer.createDeck();
				game.dealToPlayer();
				game.dealToSelf();
			
				System.out.println("Player's cards: " );
				for(int i = 0; i < game.getPlayerCardsSize(); i++) {
					System.out.print(game.player.playerCards.get(i).getRank() + "" + game.player.playerCards.get(i).getSuit() + " ");
				}
				game.player.updateScore();
				System.out.println("");
				System.out.println("Player's score: " + game.player.getScore());
		//		System.out.println("");
				System.out.println("Dealer's cards: ");
				for(int i = 0; i < game.dealer.dealerCards.size(); i++) {
					if(game.dealer.dealerCards.get(i).getFaceUp() == true) {
						System.out.println(game.dealer.dealerCards.get(i).getRank() + "" + game.dealer.dealerCards.get(i).getSuit());
						game.dealer.updateScore();
					}
				}
				System.out.println("Dealer score: " + game.dealer.getScore());

				win = game.winCheck();
				System.out.println("win: " + win);
				input.nextLine();
			
				while (win==0) {
					
					System.out.println("Enter 'h' to hit, 's' to stand");	//player's turn
					play = input.nextLine();
					if(play.compareTo("h")==0) {
						game.hit();
						System.out.println("Player hits");
						System.out.println("Player's cards: ");
						for(int i = 0; i < game.getPlayerCardsSize(); i++) {
							System.out.print(game.player.playerCards.get(i).getRank() + "" + game.player.playerCards.get(i).getSuit() + " ");
						}
						System.out.println("");
						game.player.updateScore();
						win = game.winCheck();
						System.out.println("win: " + win);
						System.out.println("Player's score: " + game.player.getScore());
						
						/*TEMP*/
						if(win == 1) {
							break;
						}
 
					}else if(play.compareTo("s") == 0) {
						game.stand();
						System.out.println("Player stands");
						System.out.println("Player's cards: ");
						for(int i = 0; i < game.getPlayerCardsSize(); i++) {
							System.out.print(game.player.playerCards.get(i).getRank() + "" + game.player.playerCards.get(i).getSuit() + " ");
						}

						System.out.println("");
						game.player.updateScore();
						
						System.out.println("Player's score: " + game.player.getScore());

					}
					//win = game.winCheck();
					game.dealer.flipCard();		 			
					game.dealerPlay();
					System.out.println("Dealer's cards: ");
					for(int i = 0; i < game.dealer.dealerCards.size(); i++) {
						if(game.dealer.dealerCards.get(i).getFaceUp() == true) {
							System.out.print(game.dealer.dealerCards.get(i).getRank() + "" + game.dealer.dealerCards.get(i).getSuit() + " ");
							game.dealer.updateScore();
						}
					}
					System.out.println("");
					System.out.println("Dealer score: " + game.dealer.getScore());

					
					win = game.winCheck();
					/*TEMP*/
					if(win == 2) {
						break;
					}
				}
				
				if(win == 1) {
					System.out.println("Dealer wins with a score of " + game.dealer.getScore());
				}else if(win == 2) {
					System.out.println("Player wins with a score of: " + game.player.getScore());
				}
			
				
				
			}else if (choice == 2) {	//file input
				/*PLACEHOLDER*/
				//System.out.println("NOT IMPLEMENTED");
				BufferedReader in = null;
				FileReader file = null;
				Scanner f;
				String filePath = null;
				ArrayList <String> fileContents = new ArrayList<String>();
				ArrayList <String> subList = new ArrayList<String>();
				int flag1 = 0;
				int flag2 = 0;
				int winFlag = 0;
				int dealerHitFlag = 0;
				int playerCommandFlag = 0;	//1 if a player command is seen
				boolean playerCommandsLeft = true;
				//	input.nextLine();
				//	System.out.println("Enter file path: ");
				//filePath = input.nextLine();
				try {
					f = new Scanner(new File("C:\\Users\\User\\Documents\\file.txt"));
				
			//	String temp = null;
				
				while(f.hasNext()) {
					fileContents.add(f.next());
				}
				f.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*CONSTANT OPERATIONS ARE HARDCODED*/
				for(int i = 0; i < fileContents.size(); i++) {	//game can only go as long as the number of commands in the text file
					if(i==0 || i == 1) {					
						game.dealer.deck.add(game.dealer.cardGen(fileContents.get(i)));						
						if(i == 1) {
						game.player.playerCards.addAll(	game.dealer.dealToPlayer());
							System.out.println("Player's cards: ");
							for(int j = 0; j < game.getPlayerCardsSize(); j++) {
								System.out.print(game.player.playerCards.get(j).getRank() + "" + game.player.playerCards.get(j).getSuit() + " ");
							}
							System.out.println("");
							game.player.updateScore();
							System.out.println("Player's score: " + game.player.getScore());							
							/*if(game.blackJackTest() == 2 && flag1 == 0) {
								System.out.println("Player has a blackjack");
								flag1 = 1;
							}*/
						}
					}					
					if(i==2 || i == 3) {
						game.dealer.deck.add(game.dealer.cardGen(fileContents.get(i)));
						if(i == 3) {
							game.dealer.dealToSelf();
							System.out.println("Dealer's cards: ");
							for(int k = 0; k < game.dealer.dealerCards.size(); k++) {
								if(game.dealer.dealerCards.get(k).getFaceUp() == true) {
									System.out.print(game.dealer.dealerCards.get(k).getRank() + "" + game.dealer.dealerCards.get(k).getSuit() + " ");
								}
							}
							System.out.println("");
							game.dealer.updateScore();
							System.out.println("Dealer's score: " + game.dealer.getScore());							
							/*if(game.blackJackTest()==1 && flag2==0) {
								System.out.println("Dealer has a blackjack");
								flag2 = 1;
							}*/
						
						}
					}
								
					if(fileContents.get(i).equals("H")) {	//player hits with card at i+1-> dealer autoplays with card at i+1 (unsafe)
						//prepare card
						//playerCommandFlag =1;
						game.dealer.deck.add(game.dealer.cardGen(fileContents.get(i+1)));	//this is unsafe
						game.hit();
						System.out.println("Player hits");
						System.out.println("Player's cards: ");
						for(int j = 0; j < game.getPlayerCardsSize(); j++) {
							System.out.print(game.player.playerCards.get(j).getRank() + "" + game.player.playerCards.get(j).getSuit() + " ");
						}
						System.out.println("");
						game.player.updateScore();
						System.out.println("Player's score: " + game.player.getScore());							
						/*if(game.blackJackTest() == 2 && flag1 == 0) {
							System.out.println("Player has a blackjack");
							flag1 = 1;
						}*/
						
						
						
					}else if(fileContents.get(i).equals("S")) {	//player stands -> dealer autoplays with card at i+1 (unsafe)
						//playerCommandFlag =1;
						System.out.println("Player stands");
						System.out.println("Player's cards: ");
						for(int j = 0; j < game.getPlayerCardsSize(); j++) {
							System.out.print(game.player.playerCards.get(j).getRank() + "" + game.player.playerCards.get(j).getSuit() + " ");
						}
						System.out.println("");
						game.player.updateScore();
						System.out.println("Player's score: " + game.player.getScore());							
					/*	if(game.blackJackTest() == 2 && flag1 == 0) {
							System.out.println("Player has a blackjack");
							flag1 = 1;
						}*/
						
						game.dealer.flipCard();
						if(game.softCheck()== true || game.dealer.getScore() <=16) {
							game.dealer.deck.add(game.dealer.cardGen(fileContents.get(i+1)));
							game.dealerHit();
							System.out.println("Dealer hits");
							for(int j = 0; j < game.dealer.dealerCards.size(); j++) {
								System.out.print(game.dealer.dealerCards.get(j).getRank() + "" + game.dealer.dealerCards.get(j).getSuit() + " ");

							}
							
							System.out.println("");
							game.dealer.updateScore();
							System.out.println("Dealer's score: " + game.dealer.getScore());							
						/*	if(game.blackJackTest()==1 && flag2==0) {
								System.out.println("Dealer has a blackjack");
								flag2 = 1;
							}*/
						}else {
							System.out.println("Dealer stands");
							for(int j = 0; j < game.dealer.dealerCards.size(); j++) {
								System.out.print(game.dealer.dealerCards.get(j).getRank() + "" + game.dealer.dealerCards.get(j).getSuit() + " ");

							}
							
							System.out.println("");
							game.dealer.updateScore();
							System.out.println("Dealer's score: " + game.dealer.getScore());							
						/*	if(game.blackJackTest()==1 && flag2==0) {
								System.out.println("Dealer has a blackjack");
								flag2 = 1;
							}*/
						}
					}
					
					
					
				/*	if(playerCommandFlag==1) {
						//dealer's turn: only moves after there are no more player instructions left in the list
						for(int j = i; j < fileContents.size(); j++) {
							subList.add(fileContents.get(j));
						}
					
						for(int j = 0; j < subList.size(); j++) {
							if(subList.get(j).equals("H") || subList.get(j).equals("S")) {
								playerCommandsLeft = true;
							}else {
								playerCommandsLeft = false;
							}
						}
						subList.clear();
					
					}*/
					
				/*	if(game.softCheck()==true||game.dealer.getScore()<=16) {
						System.out.println("Dealer Hits");
						int l = i+1;
						game.dealer.deck.add(game.dealer.cardGen(fileContents.get(l)));
						game.dealerHit();
						dealerHitFlag =1; 	//dealer has hit
						
					}else {
						System.out.println("Dealer stands");
 					}*/
					
				}
				
				/*if(game.blackJackTest()==2) {
					System.out.println("Player has a blackjack");
				}else if(game.blackJackTest()==1) {
					System.out.println("Dealer has a blackjack");
				}*/
				
				/*Check for win after the file contents are processed*/
				/*if(game.winCheck()== 1) {
					System.out.println("Dealer wins");
					return;
				}else if(game.winCheck()==2) {
					System.out.println("Player wins");
					return;
				}*/
				winFlag = game.winCheck();
				if(winFlag == 1) {
					System.out.println("Dealer wins");
				}else if(winFlag == 2) {
					System.out.println("Player wins");
				}
 			
				
			}
		}
}
