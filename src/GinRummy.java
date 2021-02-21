import java.util.Scanner;
import java.util.ArrayList;
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
     * Distribute cards.
     * 10 cards per player, next card on the discard pile
     */
    private static void distributeCards(){
        for(int i = 0; i < 10; i++){
            p1.addCardToHand(stockPile.pop());
            cpu.addCardToHand(stockPile.pop());
        }
        discardPile.push(stockPile.pop());
    }

    /**
     * Print method for displaying user's options
     */
    private static void displayChoices(){
        System.out.println("\nWhat would you like to do? Input 1-4 to make your choice.");
        System.out.println("1. Draw a card from the stock pile.");
        System.out.println("2. Draw the card from the discard pile.");
        System.out.println("3. Check melds.");
        System.out.println("4. Check deadwood score/attempt to knock.");
    }

    /**
     * Draw from the stock pile
     */
    private static void drawFromStockPile(){
        Card c = stockPile.pop();
        displayAddDraw(c);
    }

    /**
     * Draw from discard pile
     */
    private static void drawFromDiscardPile(){
        Card c = discardPile.pop();
        displayAddDraw(c);
    }

    /**
     * Display and add the drawn card to hand
     */
    private static void displayAddDraw(Card c){
        System.out.println("You drew: " + c.toString());
        p1.addCardToHand(c);
    }

    /**
     * Discard from hand
     */
    private static void discardCard(){
        System.out.println("Choose a card to discard: ");
        for(Card c: p1.getHand()){
            System.out.print(c.toString() + " ");
        }
        System.out.println("");
        String discard = scanner.nextLine();
        p1.getHand().remove(discard);
    }

    /**
     * Player makes decisions
     * 
     * @return true if the player knocks, false otherwise
     */
    private static boolean playerDecision(){
        // hand
        System.out.println("Your hand: ");
        for(Card c: p1.getHand()){
            System.out.print(c.toString() + " ");
        }
        
        // check melds
        p1.checkMelds();
        // recalculate deadwood score
        p1.recalculateDeadwoodScore();
        
        // choices
        displayChoices();
        int choice = scanner.nextInt();
        scanner.nextLine();

        // lock user and prevent them from advancing if input is incorrect
        while(choice < 1 || choice > 4){
            System.out.println("Invalid input. Enter a value between 1 and 4.");
            choice = scanner.nextInt();
            scanner.nextLine();
        }

        do{
            switch(choice){
                case 1:
                    drawFromStockPile();
                    discardCard();
                    break;
                case 2:
                    drawFromDiscardPile();
                    discardCard();
                    break;
                case 3:
                    ArrayList<ArrayList<Card>> melds = p1.getMelds();
                    for(ArrayList<Card> meld: melds){
                        for(Card c: meld){
                            System.out.print(c.toString() + " ");
                        }
                        System.out.println();
                    }
                    displayChoices();
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Your current deadwood score: " + p1.getDeadwoodScore());
                    if(p1.getDeadwoodScore() <= 10){
                        System.out.println("Would you like to knock? (Y/N)");
                        char knock = scanner.nextLine().toLowerCase().charAt(0);
                        if(knock == 'Y'){
                            return true;
                        }
                    }
                    displayChoices();
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
            }
        }while(choice == 3 || choice == 4);

        return false;
    }

    /**
     * Calculate score: The absolute value of the difference of 
     * the deadwood scores is calculated, and the points are 
     * awarded to the player with the lowest deadwood score.
     * If any of the players have 0 deadwood points and knocks,
     * they get an additional 20 points.
     */
    private static void calculateScores(){
        int playerScore = p1.getDeadwoodScore();
        int cpuScore = cpu.getDeadwoodScore();
        int difference = Math.abs(playerScore - cpuScore);
        if(playerScore < cpuScore){
            p1.addToTotalScore(difference);
        }
        else{
            cpu.addToTotalScore(difference);
        }
    }

    /**
     * Reset all elements of the former deal
     */
    private static void resetEverything(){
        p1.resetDeadwoodScore();
        p1.resetHand();
        p1.resetMelds();
        cpu.resetDeadwoodScore();
        cpu.resetHand();
        cpu.resetMelds();
    }

    /**
     * Play a single deal
     */
    private static void playDeal(){
        // reset everything
        resetEverything();

        // distribute cards
        distributeCards();

        boolean playerKnocks = false;
        boolean cpuKnocks = false;
        while(!cpuKnocks || !playerKnocks){
            System.out.print("Card on the discard pile: ");
            discardPile.displayTopCard();

            // player decides on what to do
            playerKnocks = playerDecision();
            
            if(playerKnocks){
                break;
            }
            // cpu decides on what to do
            cpuKnocks = Computer.makeMove(cpu, stockPile, discardPile);
        }

        // calculate score based on who knocked
        calculateScores();
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
    public static void main(String[] args){
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
