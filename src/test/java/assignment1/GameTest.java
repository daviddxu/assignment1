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
	
	/*tests deckGen() (Dealer)*/
	@Test
	public void deckGenTest() {	//test if a 52 card deck is generated		
		Dealer dealerTest = new Dealer();
		dealerTest.createDeck();
		assertEquals("Deck must have 52 cards", 52, dealerTest.deckSize());		
	}
	/*tests shuffling operation in deckGen() using createUnshuffledDeck() (Dealer)*/
	@Test 
	public void shuffleTest() {	//test if deck has been shuffled*
		Dealer dealerTest = new Dealer();
		assertEquals("Equal Test should return false", false, dealerTest.equalDeck());
		//*tests if two cards are in the same positions in both decks	
	}
	/*tests dealToPlayer() (Dealer) */
	@Test
	public void dealToPlayerTest() { //test dealer deal to player function		
		Game game = new Game();
		game.dealer.createDeck();
		game.dealToPlayer();
		assertEquals("Player must have two cards", 2, game.getPlayerCardsSize());
	}
	/*tests dealToSelf() (Dealer)*/
	@Test
	public void dealToSelfTest() {
		Dealer dealer = new Dealer();
		dealer.createDeck();
		dealer.dealToSelf();
		assertEquals("Dealer must have two cards", 2, dealer.getDealerCardsSize());
	}

	/*tests winCheck() (Game) */
	@Test
	public void winCheckTest() {
		Game game = new Game();
		game.dealer.dealerCards.add(new Card("9", 9, true, "D"));
		game.dealer.dealerCards.add(new Card("9", 9, true, "A"));
		game.dealer.dealerCards.add(new Card("2", 2, true, "H"));	
		game.dealer.updateScore();
		assertEquals("Dealer wins (score: 21)", 1, game.winCheck());
		game.dealer.dealerCards.add(new Card("3", 3, true, "S"));
		game.dealer.updateScore();
		assertEquals("Player wins (dealer busted)", 2, game.winCheck());
		game.dealer.dealerCards.clear();
		game.dealer.updateScore();
		game.player.playerCards.add(new Card("9", 9, true, "D"));
		game.player.playerCards.add(new Card("9", 9, true, "A"));
		game.player.playerCards.add(new Card("2", 2, true, "H"));
		game.player.updateScore();
		assertEquals("Player wins (score: 21)", 2, game.winCheck());
		game.player.playerCards.add(new Card("3", 3, true, "S"));
		game.player.updateScore();
		assertEquals("Dealer wins (player busted)", 1, game.winCheck());
		game.player.playerCards.clear();
		
		game.player.playerCards.add(new Card("10", 10, true, "H"));
		game.player.playerCards.add(new Card("A", 11, true, "A"));
		assertEquals("Player has a blackjack", 2, game.blackJackTest());	//calls blackjack check function w/o accessing getScore
		game.dealer.dealerCards.add(new Card("10", 10, true, "H"));
		game.dealer.dealerCards.add(new Card("A", 11, true, "A"));
		assertEquals("Dealer has a blackjack", 1, game.blackJackTest());			
	}
	
	/*test bustCheck() (Game)*/
	@Test
	public void bustTest() {	//check if player has busted: exceeded 21 points
		
		Game game = new Game();
		Card c1 = new Card("J", 10, true, "D");
		Card c2 = new Card("Q", 10, true, "H");
		Card c3 = new Card("K", 10, true, "A");
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);
		game.dealToPlayer();
		game.player.playerCards.add(c3);
		game.player.updateScore();
		assertEquals("Player has busted (30) (return 1)", 1, game.bustCheck());

	}
	
	/*test hit() (Game)*/
	@Test
	public void playerHitTest() {	//test player's hit: deal the player another card and check if player busts
		
		Game game = new Game();		
		game.dealer.createDeck();
		game.dealToPlayer();	
		game.hit();
		assertEquals("Player should have 3 cards", 3, game.player.playerCards.size());
	}
	
	/*test stand() (Game)*/
	@Test
	public void standTest() {	//test player's stand: mark all cards on table face up 
		
		Game game = new Game();		
		game.dealer.createDeck();
		game.dealToPlayer();
		game.stand();	//calls dealer.stand()
		assertEquals("All of dealer's cards should be face up: ", true, game.dealer.standTest());		
	}
	
	/*test dealerHit() (Game)*/
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
		game.dealer.deck.add(new Card("A", 1, true, "S"));
		game.dealerHit();
		assertEquals("The dealer should now have four cards", 4, game.dealer.dealerCards.size());

	
	}
		
	/*test blackJackTest for Player (Game) */
	@Test
	public void playerBlackjackTest() {	//test blackjack checker function: if a player has an ace and 10, J, Q, K, they win; check dealer first then player; 
	
		Game game = new Game();
		Card c1 = new Card("A", 1, true, "S");
		Card c2 = new Card("10", 10, true, "H");		
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);		
		game.dealToPlayer();		
		assertEquals("The player should have blackjack (2)", 2, game.blackJackTest());
	}
	
	/*test blackJackTest for Dealer (Game)*/
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
	
	/*test blackJackTest for Dealer precedence (Game)*/
	public void blackJackPrecedenceTest() {
		Game game = new Game();
		game.player.playerCards.add(new Card("A", 1, true, "S"));
		game.player.playerCards.add(new Card("J", 10, true, "S"));
		game.player.updateScore();
		game.dealer.dealerCards.add(new Card("A", 1, true, "H"));
		game.player.playerCards.add(new Card("J", 10, true, "H"));
		game.dealer.updateScore();
		assertEquals("dealer should win", 1, game.dealer.getScore());	//calls blackJackTest() (Game)
	}
	
	
	/*test genPoints() (Dealer)*/
	@Test 
	public void genPointsTest() {	//test genPoints function in Dealer
		
		Game game = new Game();		
		assertEquals("genPoints should return 1", 1, game.dealer.genPoints("A"));
		assertEquals("genPoints should return 2", 2, game.dealer.genPoints("2"));
		assertEquals("genPoints should return 3", 3, game.dealer.genPoints("3"));
		assertEquals("genPoints should return 4", 4, game.dealer.genPoints("4"));
		assertEquals("genPoints should return 5", 5, game.dealer.genPoints("5"));
		assertEquals("genPoints should return 6", 6, game.dealer.genPoints("6"));
		assertEquals("genPoints should return 7", 7, game.dealer.genPoints("7"));
		assertEquals("genPoints should return 8", 8, game.dealer.genPoints("8"));
		assertEquals("genPoints should return 9", 9, game.dealer.genPoints("9"));
		assertEquals("genPoints should return 10", 10, game.dealer.genPoints("10"));
		assertEquals("genPoints should return 10", 10, game.dealer.genPoints("J"));
		assertEquals("genPoints should return 10", 10, game.dealer.genPoints("Q"));
		assertEquals("genPoints should return 10", 10, game.dealer.genPoints("K"));
		
	}
	
	/*test flipCard() (Dealer)*/
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
	
	/*helper function for flipCardTest()*/
	public boolean flipCardTestHelper(ArrayList<Card> cards) {	//helper function for flipCardTest
		
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getFaceUp() == false) {
				return false;
			}
		}
		return true;
	}
	
	/*test aceCheck() (Game)*/
	@Test
	public void aceCheckTest() {
		Game game = new Game();
		
		game.dealer.dealerCards.add(new Card("A", 1, true, "S"));
		assertEquals("Ace should equal 1", 1, game.dealer.dealerCards.get(0).getPoints());
		game.aceCheck();
		assertEquals("Ace should equal 11", 11, game.dealer.dealerCards.get(0).getPoints());
		game.dealer.dealerCards.add(new Card("A", 1, true, "H"));
		game.aceCheck();
		assertEquals("New Ace should equal 1", 1, game.dealer.dealerCards.get(1).getPoints());
		game.dealer.updateScore();
		assertEquals("total points should be 12", 12, game.dealer.getScore());		//one Ace is 11 points the other is 1 point
		game.dealer.dealerCards.clear();
		game.dealer.dealerCards.add(new Card("A", 1, true, "S"));
		game.dealer.dealerCards.add(new Card("A", 1, true, "H"));
		game.dealer.dealerCards.add(new Card("J", 10, true, "H"));
		game.aceCheck();
		
		game.dealer.updateScore();
		assertEquals("total points should be 12", 12, game.dealer.getScore());		//both aces are 1 point to avoid busting
		
		
		
		
	}
	/*test updateScore() (Dealer)*/
	@Test
	public void updateDealerScoreTest() {	//test updateScore in Dealer class
		Game game = new Game();		
 		Card c1 = new Card("2", 2, true, "S");
		Card c2 = new Card("5", 5, true, "D");
		game.dealer.deck.add(c1);
		game.dealer.deck.add(c2);
		assertEquals("Score should be 0", 0 , game.dealer.getScore());
		game.dealer.dealToSelf();	
		game.dealer.updateScore();
		assertEquals("New score should be 7", 7, game.dealer.getScore());		
	}
	
	/*test updateScore() (Dealer)*/
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
	
	/*test softCheck() (Game)*/
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
			assertEquals("game.softCheck() should return true", true, game.softCheck());	//soft 17 detected
			
	}

	/*test genPoints() (Dealer)*/
	@Test
	public void generatePointsTest() {	//test genPoints for dealer
		
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
	
	
	/*test cardGen() (Dealer)*/
	@Test
	public void cardGenTest() {	//test card generation from input string
		
		Game game = new Game();
		Card c1 = game.dealer.cardGen("SK");
		game.dealer.dealerCards.add(c1);		
		assertEquals("rank should be K", "K", game.dealer.dealerCards.get(0).getRank());
		assertEquals("suit should be S", "S", game.dealer.dealerCards.get(0).getSuit());
		assertEquals("should be face up", true, game.dealer.dealerCards.get(0).getFaceUp());
		assertEquals("should be 10", 10, game.dealer.dealerCards.get(0).getPoints());
		game.dealer.dealerCards.clear();
		
		Card c2 = game.dealer.cardGen("S10");
		game.dealer.dealerCards.add(c2);
		System.out.println(game.dealer.dealerCards.get(0).getRank());
		assertEquals("rank should be 10", "10", game.dealer.dealerCards.get(0).getRank());
		assertEquals("suit should be S", "S", game.dealer.dealerCards.get(0).getSuit());
		assertEquals("should be face up", true, game.dealer.dealerCards.get(0).getFaceUp());
		assertEquals("should be 10", 10, game.dealer.dealerCards.get(0).getPoints());		
	}
	
	
	/*test dealerPlay() (Game)*/
	@Test
	public void dealerPlayTest() {	//test dealer's autoplay abilities
		
		Game game = new Game();
		game.dealer.dealerCards.add(new Card("2", 2, true, "S"));
		game.dealer.dealerCards.add(new Card("3", 3, true, "H"));
		game.dealer.updateScore();
		game.dealer.deck.add(new Card("5", 5, true, "S"));
		assertEquals("dealer's points should be 5", 5, game.dealer.getScore());
		assertEquals("dealer should have 2 cards", 2, game.dealer.dealerCards.size());

		game.dealerPlay();
		game.dealer.updateScore();
		assertEquals("dealer's points should now be 10", 10, game.dealer.getScore());
		assertEquals("dealer should now have 3 cards", 3, game.dealer.dealerCards.size());
		game.dealer.dealerCards.clear();
		
		game.dealer.dealerCards.add(new Card("10", 10, true, "S"));
		game.dealer.dealerCards.add(new Card("9", 9, true, "H"));
		game.dealer.updateScore();
		assertEquals("dealer's points should be 19", 19, game.dealer.getScore());
		assertEquals("dealer should have two cards", 2, game.dealer.dealerCards.size());
		game.dealer.deck.add(new Card("5", 5, true, "S"));
		game.dealerPlay();
		assertEquals("dealer's points should still be 19", 19, game.dealer.getScore());
		assertEquals("dealer should still have two cards", 2, game.dealer.dealerCards.size());

		game.dealerPlay();
		
		
		
		
	}
}
