package assignment1;

/*
 * GameTest.java 
 * TDD tester for Game.java (BlackJack) class
 * 
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	
	@Test
	public void deckGenTest() {	//test if a 52 card deck is generated
		
		//not implemented
		//fail("Not yet implemented");
		Dealer dealerTest = new Dealer();
		dealerTest.createDeck();
		assertEquals("Deck must have 52 cards", 52, dealerTest.deckSize());

		
		
	}
	@Test
	public void dealToPlayerTest() { //test dealer deal to player function
		
		//not implemented
	//	fail("Not yet implemented");
		
		Game game = new Game();
		//game.dealer = new Dealer();
		//game.player = new Player();
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
		
	//	fail("Not yet implemented");
		
		Game game = new Game();
		//game.dealer = new Dealer();
		Card c1 = new Card(11, 11, true, "D");
		Card c2 = new Card(12, 12, true, "H");
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);
	//	game.player = new Player();

		game.dealToPlayer();
		//game.player.updateScore();
		
	//	game.bustCheck();
		System.out.println("Player's score: " + game.player.getScore() );
		assertEquals("Player has busted (23)", true, game.bustCheck());
		
		//game.

	}
	@Test
	public void hitTest() {	//test player's hit: deal the player another card and check if player busts
		
		Game game = new Game();
		
		game.dealer.createDeck();
		game.dealToPlayer();
	//	game.dealer.dealToSelf();
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
		/**NOT IMPLEMENTED**/
	//	fail("Not yet implemented");

		Game game = new Game();
		Card c1 = new Card(1, 1, false, "D");
		Card c2 = new Card(6, 6, true, "H");
		Card c3 = new Card(2, 2, true, "S");
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
		
		/**NOT IMPLEMENTED**/
		//fail("Not yet implemented");

		Game game = new Game();
		Card c1 = new Card();

	}
	

}
