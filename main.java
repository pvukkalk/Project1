import java.util.Scanner;


public class main {
    public static void main(String[] args) {
        Player playerX = new Player("Player 1", Mark.X);
        Player playerO = new Player("Player 2", Mark.O);
        Game game = new Game(playerX, playerO);
    }
    public static int getPos(int x, int y){
        int posX = 0;
        int posY = 0;
        Scanner scanner = new Scanner(System.in);
        while (posX < 1 || posX > 9 || posY < 1 || posY > 9) {
            System.out.print("Enter a position (1-9): ");
            posX = scanner.nextInt();
            posY = scanner.nextInt();
            try {
                if (posX < 1 || posX > 9 || posY < 1 || posY > 9) {
                    throw new IllegalArgumentException("Position must be between 1 and 9.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            return pos;
        } 
    public static void smallGame(){




    }

}
