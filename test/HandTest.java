import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HandTest {

	private static Hand hand;
	
	private static Hand handExpected;

	private static Card card_5d = new Card(Suit.D, 5); 

	private static Card card_Qs = new Card(Suit.S, 12); 

	private static Card card_3d = new Card(Suit.D, 3); 
	
	private static Card card_As = new Card(Suit.S, 1); 

	private static Card card_2s = new Card(Suit.S, 2); 

	@Before
	public void setUp() {
		hand = new Hand();
		//Set up the hand		
		hand.add(card_As);
		hand.add(card_2s);
		hand.add(card_Qs);
		hand.add(card_3d);
		hand.add(card_5d);
		
		handExpected = new Hand();
	}
	
	/**
	 * Tests FR-H-1 remove method  
	 */
	@Test
	public void testHandRemove1() {
		hand.remove("As");
		
		//Expected hand 
		handExpected.add(card_2s);
		handExpected.add(card_Qs);
		handExpected.add(card_3d);
		handExpected.add(card_5d);

		Assert.assertEquals(handExpected.toString(), hand.toString());
	}
	
	/**
	 * Tests FR-H-2 remove method  
	 */
	@Test
	public void testHandRemove2() {		
		hand.remove("Kc");

		//Expected hand 
		handExpected.add(card_As);
		handExpected.add(card_2s);
		handExpected.add(card_Qs);
		handExpected.add(card_3d);
		handExpected.add(card_5d);
		
		Assert.assertEquals(handExpected.toString(), hand.toString());
	}
	
	/**
	 * Tests FR-H-3 remove method  
	 */
	@Test
	public void testHandRemove3() {		
		hand.remove("AS");
		
		//Expected hand 
		handExpected.add(card_2s);
		handExpected.add(card_Qs);
		handExpected.add(card_3d);
		handExpected.add(card_5d);
		
		Assert.assertEquals(handExpected.toString(), hand.toString());
	}
	
	/**
	 * Tests FR-H-4 remove method 
	 */
	@Test
	public void testHandRemove4() {			
		hand.remove("KC");

		//Expected hand 
		handExpected.add(card_As);
		handExpected.add(card_2s);
		handExpected.add(card_Qs);
		handExpected.add(card_3d);
		handExpected.add(card_5d);
		
		Assert.assertEquals(handExpected.toString(), hand.toString());

	}
	
	/**
	 * Tests FR-H-5 hand display on console - Manual Check
	 */
	@Test
	public void testHandDisplay() {		
		hand.displayHand();
	}
	
	/**
	 * Tests FR-H-6 contains method
	 */
	@Test
	public void testHandContains1() {
		Boolean actual = hand.contains("As");
		
		Assert.assertEquals(true, actual);
	}
	
	/**
	 * Tests FR-H-7 contains method
	 */
	@Test
	public void testHandContains2() {
		Boolean actual = hand.contains("Ks");
		
		Assert.assertEquals(false, actual);
	}
	
	/**
	 * Tests FR-H-8 contains method
	 */
	@Test
	public void testHandContains3() {
		Boolean actual = hand.contains("AS");
		
		Assert.assertEquals(true, actual);
	}
	
	/**
	 * Tests FR-H-9 contains method
	 */
	@Test
	public void testHandContains4() {
		Boolean actual = hand.contains("KS");
		
		Assert.assertEquals(false, actual);
	}
}

