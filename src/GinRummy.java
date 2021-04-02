/**
 * @brief Main operating class of the program
 * @author Benson
 *
 */
public class GinRummy {

	/**
	 * Display current total scores of both players
	 * 
	 * @param p1 - user player
	 * @param cpu - computer player
	 */
	private static void displayCurrentScores(Player p1, Player cpu) {
		System.out.println("Current scores: ");
		System.out.print(p1.getName() + ": " + p1.getTotalScore() + "\t");
		System.out.println(cpu.getName() + ": " + cpu.getTotalScore() + "\n");
	}
	
	/**
	 * Play a single deal
	 * 
	 * @param p1  - user player
	 * @param cpu - computer player
	 * @param sp  - stock pile
	 * @param dp  - discard pile
	 */
	private static void playDeal(Player p1, Player cpu, StockPile sp, DiscardPile dp) {
		// reset everything
		GameOps.resetEverything(p1);
		GameOps.resetEverything(cpu);

		// distribute cards
		GameOps.distributeCards(p1, cpu, sp, dp);

		boolean playerKnocks = false;
		boolean cpuKnocks = false;
		while (!cpuKnocks && !playerKnocks) {
			// player decides on what to do
			playerKnocks = GameOps.processDecision(p1, sp, dp);
			if (playerKnocks) {
				break;
			}

			// cpu decides on what to do
			cpuKnocks = Computer.makeMove(cpu, sp, dp);
			System.out.println("\nAI has made their move!\n");
		}

		// reaching here means someone knocked - who?
		String knocker = playerKnocks ? p1.getName() : cpu.getName();
		System.out.println(knocker + " knocked!\n");
		
		// calculate score based on who knocked
		GameOps.calculateScores(p1, cpu);
		displayCurrentScores(p1, cpu);
	}

	/**
	 * Play a game of Gin Rummy
	 * 
	 * @param p1  - user player
	 * @param cpu - computer player
	 */
	public static void playGame(Player p1, Player cpu) {
		p1.resetTotalScore();
		cpu.resetTotalScore();
		System.out.println("Let's play a game of Gin Rummy!");

		// while either score is less than 100, play a new deal
		while (p1.getTotalScore() < 100 && cpu.getTotalScore() < 100) {
			// create new stock and discard piles
			StockPile sp = GameOps.createStockPile();
			DiscardPile dp = GameOps.createDiscardPile();

			playDeal(p1, cpu, sp, dp);
		}

		// who won?
		String winner = p1.getTotalScore() > cpu.getTotalScore() ? p1.getName() : cpu.getName();
		System.out.println(winner + " wins!");
	}

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to Rummy for Dummies!");

		// username
		String name = GameOps.username();

		Player p1 = new Player(name);
		Player cpu = new Player("AI");
		char keepPlaying = 'y';

		// start a game of rummy
		while (keepPlaying == 'y') {
			playGame(p1, cpu);
			// let player have option of playing again
			System.out.println("Play again? (y/n)");
			keepPlaying = GameOps.playAgain();
		}
		GameOps.endGame();
	}
}
