import org.junit.*;

public class HandTest {

	private static Hand hand;

	@Before
	public void setUp() {
		hand = new Hand();
		//Set up the hand		
		Card card = new Card(Suit.S, 1);
		hand.add(card);
		card = new Card(Suit.S, 2);
		hand.add(card);
		card = new Card(Suit.S, 12);
		hand.add(card);
		card = new Card(Suit.S, 3);
		hand.add(card);
		card = new Card(Suit.S, 5);
		hand.add(card);
	}
	
	/*
	 * Tests FR-H-1 remove method  
	 */
	@Test
	public void testHandRemove1() {
		Card removedCard = new Card(Suit.S, 1);
		Card actualCard = hand.remove("As");
		Assert.assertEquals(removedCard.toString(), actualCard.toString());
		Assert.assertEquals(4, hand.size());
	}
	
	/*
	 * Tests FR-H-2 remove method  
	 */
	@Test
	public void testHandRemove2() {		
		Hand handRemove = hand;
		handRemove.remove("Kc");

		Assert.assertEquals(hand, handRemove);
		Assert.assertEquals(hand.size() == 5, handRemove.size() == 5);
	}
	
	/*
	 * Tests FR-H-3 remove method  
	 */
	@Test
	public void testHandRemove3() {		
		Card removedCard = new Card(Suit.S, 1);
		Card actualCard = hand.remove("AS");
		
		Assert.assertEquals(removedCard.toString(), actualCard.toString());
		Assert.assertEquals(4, hand.size());
	}
	
	/*
	 * Tests FR-H-4 remove method 
	 */
	@Test
	public void testHandRemove4() {			
		Hand handRemove = hand;
		handRemove.remove("KC");

		Assert.assertEquals(hand, handRemove);
		Assert.assertEquals(hand.size() == 5, handRemove.size() == 5);
	}
	
	/*
	 * Tests FR-H-5 hand display on console - Manual Check
	 */
	@Test
	public void testHandDisplay() {		
		hand.displayHand();
	}
	
	/*
	 * Tests FR-H-6 contains method
	 */
	@Test
	public void testHandContains1() {
		Boolean actual = hand.contains("As");
		Assert.assertEquals(true, actual);
	}
	
	/*
	 * Tests FR-H-7 contains method
	 */
	@Test
	public void testHandContains2() {
		Boolean actual = hand.contains("Ks");
		Assert.assertEquals(false, actual);
	}
	
	/*
	 * Tests FR-H-8 contains method
	 */
	@Test
	public void testHandContains3() {
		Boolean actual = hand.contains("AS");
		Assert.assertEquals(true, actual);
	}
	
	/*
	 * Tests FR-H-9 contains method
	 */
	@Test
	public void testHandContains4() {
		Boolean actual = hand.contains("KS");
		Assert.assertEquals(false, actual);
	}
}

