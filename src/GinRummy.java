import java.util.Scanner;
import java.util.Collections;

public class GinRummy {
    private static Scanner scanner;
    private static Player p1;
    private static Player cpu;
    private static StockPile stockPile;
    private static DiscardPile discardPile;

    /**
     * Create initial stock pile and add cards to it
     */
    private static void createStockPile(){
        stockPile = new StockPile();

        // create all 52 cards and add to stock pile
        for(int i = 1; i <= 13; i++){
            for(Suit s: Suit.values()){
                Card c = new Card(s, i);
                stockPile.add(c);
            }
        }

        // shuffle stock pile
        Collections.shuffle(stockPile);
    }

    /**
     * Create discard pile
     */
    private static void createDiscardPile(){
        discardPile = new DiscardPile();
    }

    /**
     * Player makes decisions
     */
    private static void playerDecision(){
        // TODO player decides on move
    }

    /**
     * Play a single deal
     */
    private static void playDeal(){
        // TODO distribute cards, loop the decisions
        // player decides on what to do
        playerDecision();
        // cpu decides on what to do
        Computer.makeMove(cpu, stockPile, discardPile);
    }

    /**
     * Play a game of Gin Rummy
     */
    private static void playGame(){
        System.out.println("Let's play a game of Gin Rummy!");

        // while either score is less than 100, play a new deal
        while(p1.getTotalScore() < 100 && cpu.getTotalScore() < 100){
            // create new stock and discard piles
            createStockPile();
            createDiscardPile();
            
            playDeal();
        }

        // who won?
        String winner = p1.getTotalScore() > cpu.getTotalScore() ? p1.getName() : cpu.getName();
        System.out.println(winner + " wins!");
    }

    /**
     * Main method
     */
    public static void main(){
        System.out.println("Welcome to Rummy for Dummies!");
        scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        
        // create players
        p1 = new Player(name);
        cpu = new Player("AI");
        char keepPlaying = 'y';

        // start a game of rummy
        while(keepPlaying == 'y'){
            playGame();
            // let player have option of playing again
            System.out.println("Play again? (y/n)");
            keepPlaying = scanner.nextLine().toLowerCase().charAt(0);
        }
        scanner.close();
    }
}
