package assignment1;

/*
 * GameTest.java 
 * TDD tester for Game.java (BlackJack) class
 * 
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GameTest {	
	
	@Test
	public void deckGenTest() {	//test if a 52 card deck is generated
		
		Dealer dealerTest = new Dealer();
		dealerTest.createDeck();
		assertEquals("Deck must have 52 cards", 52, dealerTest.deckSize());
		
	}
	
	@Test 
	public void shuffleTest() {	//test if deck has been shuffled*
		/*Dealer dTest1 = new Dealer();
		Dealer dTest2 = new Dealer();
		dTest1.createDeck();
		dTest2.createUnshuffledDeck();*/
		
		Dealer dealerTest = new Dealer();
		assertEquals("Equal Test should return false", false, dealerTest.equalDeck());
		//*tests if two cards are in the same positions in both decks
		
	}
	
	@Test
	public void dealToPlayerTest() { //test dealer deal to player function
		
		Game game = new Game();
		game.dealer.createDeck();
		game.dealToPlayer();
		assertEquals("Player must have two cards", 2, game.getPlayerCardsSize());
	

	}
	
	@Test
	public void dealToSelfTest() {
		Dealer dealer = new Dealer();
		dealer.createDeck();
		dealer.dealToSelf();
		assertEquals("Dealer must have two cards", 2, dealer.getDealerCardsSize());
	}
	
	@Test
	public void bustTest() {	//check if player has busted: exceeded 21 points
		
		Game game = new Game();
		Card c1 = new Card("J", 11, true, "D");
		Card c2 = new Card("Q", 12, true, "H");
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);
		game.dealToPlayer();
		//System.out.println("Player's score: " + game.player.getScore() );
		assertEquals("Player has busted (23) (return 1)", 1, game.bustCheck());
		

	}
	
	@Test
	public void hitTest() {	//test player's hit: deal the player another card and check if player busts
		
		Game game = new Game();
		
		game.dealer.createDeck();
		game.dealToPlayer();	
		game.hit();
		assertEquals("Player should have 3 cards", 3, game.player.playerCards.size());
		

	}
	
	@Test
	public void standTest() {	//test player's stand: mark all cards on table face up 
		
		Game game = new Game();
		
		game.dealer.createDeck();
		game.dealToPlayer();
		game.hit();
		assertEquals("All of dealer's cards should be face up: ", true, game.dealer.standTest());


		
	}
	
	@Test
	public void dealerHitTest() {	//test dealer's play: if <= 16 points or a "soft 17", dealer hits otherwise stands
		
		Game game = new Game();
		Card c1 = new Card("A", 1, false, "D");
		Card c2 = new Card("6", 6, true, "H");
		Card c3 = new Card("2", 2, true, "S");
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);
		game.dealer.deck.add(c3);
		game.dealer.dealerCards.add(game.dealer.deck.get(0));
		game.dealer.deck.remove(0);
		game.dealer.dealerCards.add(game.dealer.deck.get(0));
		game.dealer.deck.remove(0);
		game.dealerHit();
		assertEquals("The dealer should now have three cards", 3, game.dealer.dealerCards.size());
		

	}
	
	@Test
	public void blackjackTest() {	//test blackjack checker function: if a player has an ace and 10, J, Q, K, they win; check dealer first then player; 
		

		Game game = new Game();
		Card c1 = new Card("A", 1, true, "S");
		Card c2 = new Card("10", 10, true, "H");		
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);		
		game.dealToPlayer();		
		assertEquals("The player should have blackjack (2)", 2, game.blackJackTest());
	}
	
	@Test
	public void dealerBlackJackTest() {	//test blackjack for dealer
		
		Game game = new Game();
		Card c1 = new Card("A", 1, true, "S");
		Card c2 = new Card("10", 10, true, "H");
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);
		game.dealer.dealToSelf();
		assertEquals("The dealer should have blackjack (1)", 1, game.blackJackTest());
		
		
	}
	
	@Test 
	public void genPointsTest() {	//test genPoints function in Dealer
		
		Game game = new Game();
		//Card c1 = new Card("A", 1, true)
		String rank = "A";
		
		assertEquals("genPoints should return 1", 1, game.dealer.genPoints(rank));
	}
	
	@Test 
	public void flipCardTest() {
		
		Game game = new Game();
		
		Card c1 = new Card("2", 2, false, "S");
		Card c2 = new Card("5", 5, true, "D");
		
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);
		
		game.dealer.dealToSelf();
		
		game.dealer.flipCard();
		assertEquals("flipCardTestHelper should return true", true, flipCardTestHelper(game.dealer.dealerCards));
		
		
	}
	
	public boolean flipCardTestHelper(ArrayList<Card> cards) {
		
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getFaceUp() == false) {
				return false;
			}
		}
		return true;
	}
	
	
	@Test
	public void updateDealerScoreTest() {	//test updateScore in Dealer class
		Game game = new Game();		
 		Card c1 = new Card("2", 2, true, "S");
		Card c2 = new Card("5", 5, true, "D");
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);
		game.dealer.dealToSelf();	
		game.dealer.updateScore();
		assertEquals("New score should be 2", 2, game.dealer.getScore());
		
		
		
	}
	
	@Test
	public void updatePlayerScoreTest() {	//test updateScore in Player class
		
		Game game = new Game();
		Card c1 = new Card("2", 2, true, "S");
		Card c2 = new Card("5", 5, true, "D");
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);
		game.player.playerCards.addAll(game.dealer.dealToPlayer());
		game.player.updateScore();
		assertEquals("New score should be 7", 7, game.player.getScore());
			
	}
	@Test
	public void softCheckTest() {	//test game.softCheck
			Game game = new Game();
			Card c1 = new Card("A", 11, true, "S");
			Card c2 = new Card("6", 6, true, "D");
			game.dealer.deck.add(c1);
			game.dealer.deck.add(c2);
			game.dealer.dealToSelf();
			game.dealer.flipCard();
			game.dealer.updateScore();
			assertEquals("game.softCheck() should return true", true, game.softCheck());
			
	}

	
	@Test
	public void getPointsTest() {
		
		Game game = new Game();
		
		assertEquals("game.genPoints should return '1'", 1, game.dealer.genPoints("A"));
		assertEquals("game.genPoints should return '2'", 2, game.dealer.genPoints("2"));
		assertEquals("game.genPoints should return '3'", 3, game.dealer.genPoints("3"));
		assertEquals("game.genPoints should return '4'", 4, game.dealer.genPoints("4"));
		assertEquals("game.genPoints should return '5'", 5, game.dealer.genPoints("5"));
		assertEquals("game.genPoints should return '6'", 6, game.dealer.genPoints("6"));
		assertEquals("game.genPoints should return '7'", 7, game.dealer.genPoints("7"));
		assertEquals("game.genPoints should return '8'", 8, game.dealer.genPoints("8"));
		assertEquals("game.genPoints should return '9'", 9, game.dealer.genPoints("9"));
		assertEquals("game.genPoints should return '10'", 10, game.dealer.genPoints("10"));
		assertEquals("game.genPoints should return '10'", 10, game.dealer.genPoints("J"));
		assertEquals("game.genPoints should return '10'", 10, game.dealer.genPoints("Q"));
		assertEquals("game.genPoints should return '10'", 10, game.dealer.genPoints("K"));
		assertEquals("game.genPoints should return '-1'", -1, game.dealer.genPoints("E"));

		








		


		

		
		
		
		
	}
}
