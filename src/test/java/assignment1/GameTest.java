package assignment1;

/*
 * GameTest.java 
 * TDD tester for Game.java (BlackJack) class
 * 
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void deckGenTest() {	//test if a 52 card deck is generated
		
		//not implemented
		//fail("Not yet implemented");
		Dealer dealerTest = new Dealer();
		dealerTest.createDeck();
		assertEquals("Deck must have 52 cards", 52, dealerTest.deckSize());

		
		
	}
	@Test
	public void dealTest() { //test dealer deal function: deal two cards to player and two to dealer
		
		//not implemented
		fail("Not yet implemented");

	}
	
	@Test
	public void bustTest() {	//check if player has busted: exceeded 21 points
		
		/**NOT IMPLEMENTED**/
		fail("Not yet implemented");

	}
	@Test
	public void hitTest() {	//test player's hit: deal the player another card and check if player busts
		
		/**NOT IMPLEMENTED**/
		fail("Not yet implemented");

	}
	
	@Test
	public void standTest() {	//test player's stand: mark all cards on table face up 
		/**NOT IMPLEMENTED**/
		fail("Not yet implemented");


		
	}
	
	@Test
	public void dealerPlayTest() {	//test dealer's play: if <= 16 points or a "soft 17", dealer hits otherwise stands
		/**NOT IMPLEMENTED**/
		fail("Not yet implemented");


	}
	
	@Test
	public void blackjackTest() {	//test blackjack checker function: if a player has an ace and 10, J, Q, K, they win; check dealer first then player; 
		
		/**NOT IMPLEMENTED**/
		fail("Not yet implemented");


	}
	

}
