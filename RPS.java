import java.util.Random;
import java.util.Scanner;


/**
 *  Resolved a tie by playing rock-paper-sciccors between Player and Computer
 * 
 *  @author prvuk
 *  @version Sep 16, 2026
 */
public class RPS {
    
    private static final String[] CHOICES = {"rock", "paper", "scissors"};
    
    
    /**
     * Plays rock-paper-scissors, replaying on a tie, until a winner is
     * decided. Sets each object's choice field along the way.
     * @param input shared Scanner for reading player input
     * @param player the Player object
     * @param computer the Computer object
     * @return the marker ("X" or "O") of the winner, to claim the tied spot
     */
    public static String resolveTie(Scanner input, Player player, Computer computer) {
        String winnerMarker = null;
 
        
        //while there is no winner
        while (winnerMarker == null) {
            System.out.println("\nThe game is a tie! Rock-paper-scissors decides who claims it.");
 
            String playerChoice = getValidPlayerChoice(input);
            player.setChoice(playerChoice);
 
            String computerChoice = getComputerChoice();
            computer.setChoice(computerChoice);
 
            System.out.println(player.getName() + " chose " + playerChoice + ".");
            System.out.println("Computer chose " + computerChoice + ".");
 
            int result = compare(playerChoice, computerChoice);
 
            if (result == 0) {
                System.out.println("It's a tie again! Replaying...");
            } else if (result > 0) {
                System.out.println(player.getName() + " wins the tiebreaker!");
                winnerMarker = player.getMarker();
            } else {
                System.out.println("Computer wins the tiebreaker!");
                winnerMarker = computer.getMarker();
            }
        }
 
        return winnerMarker;
    }
 
    /**
     * Prompts the player until they enter a valid choice.
     * @param input Scanner to read from
     * @return "rock", "paper", or "scissors"
     */
    private static String getValidPlayerChoice(Scanner input) {
        String choice;
        while (true) {
            System.out.println("Choose rock, paper, or scissors: ");
            choice = input.nextLine().trim().toLowerCase();
            if (choice.equals("rock") || choice.equals("paper") || choice.equals("scissors")) {
                return choice;
            }
            System.out.println("Invalid choice. Please type rock, paper, or scissors.");
        }
    }
 
    /**
     * Picks a random choice for the computer.
     * @return "rock", "paper", or "scissors"
     */
    private static String getComputerChoice() {
        Random r = new Random();
        return CHOICES[r.nextInt(CHOICES.length)];
    }
 
    /**
     * Compares two rock-paper-scissors choices.
     * @param playerChoice the player's choice
     * @param computerChoice the computer's choice
     * @return positive if player wins, negative if computer wins, 0 if tie
     */
    private static int compare(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return 0;
        }
        if ((playerChoice.equals("rock") && computerChoice.equals("scissors"))
                || (playerChoice.equals("paper") && computerChoice.equals("rock"))
                || (playerChoice.equals("scissors") && computerChoice.equals("paper"))) {
            return 1;
        }
        return -1;
    }
}
