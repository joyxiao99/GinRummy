import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Benson
 *
 */
public class UserInputOpsTest {

	private static Player p1;
	private static Player p2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests FR-UIO-1 and 2: Only valid input for knocking. If an invalid input is
	 * given, repeat prompt until proper input given.
	 */
	@Test
	public void testKnocking() {
		System.out.println("Test knock");
		char c = UserInputOps.knock();
		assertTrue(c == 'y' || c == 'n');
	}

	/**
	 * Tests FR-UIO-3 and 4: Only valid input for playing again. If an invalid input
	 * is given, repeatedly prompt until proper input given.
	 */
	@Test
	public void testPlayAgain() {
		System.out.println("Test play again");
		char c = UserInputOps.playAgain();
		assertTrue(c == 'y' || c == 'n');
	}

	/**
	 * Tests FR-UIO-5 and 6: Only valid input for making a decision. If an invalid
	 * input is given, repeatedly prompt until proper input given.
	 */
	@Test
	public void testPlayDecision() {
		System.out.println("Test choice");
		int choice = UserInputOps.playerDecision();
		assertTrue(choice >= 1 && choice <= 4);
	}

	/**
	 * Tests FR-UIO-7: Receive input for username
	 */
	public void testUsername() {
		System.out.println("Test username");
		String user = UserInputOps.username();
		assertNotNull(user);
	}
}
