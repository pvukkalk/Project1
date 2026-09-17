/**
 *  Class to create a Player object.
 *  @author prvuk
 *  @version Sep 12, 2026
 */
public class Player{
    //~ Fields ................................................................
    private String marker; //Player will be given a choice, X or O
    private String name;  //name of Player
    private int moveRow;    //their marker placement 1-9
    private int moveCol;    //their marker placement 1-9
    private String choice; //Tie-breaker, RPS choice
    

    //~ Constructors ..........................................................
    /**
     * Creates a player object
     * @param name - name of player
     */
    public Player(String name) {
        this.name = name;
    }

    //~Public  Methods ........................................................
    //-------------------------------------------
    
    /**
     * Updates marker
     * @param marker - chosen X or o
     */
    public void setMarker(String marker) {
        this.marker = marker;
    }
    
    /**
     * Updates name
     * @param name - name of the player
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Updates move
     * @param moveRow - placement of marker
     */
    public void setMoveRow(int moveRow) {
        this.moveRow = moveRow;
    }
    
    /**
     * Updates move
     * @param moveCol - placement of marker
     */
    public void setMoveCol(int moveCol) {
        this.moveCol = moveCol;
    }
    
    
    /**
     * Updates choice
     * @param choice - tie-breaker choice
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }
    //---------------------------------------------
    
    
    /**
     * Getter for marker
     * @return marker
     */
    public String getMarker() {
        return marker;
    }
    
    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Getter for move
     * @return move
     */
    public int getMoveRow() {
        return moveRow;
    }
    
    /**
     * Getter for move
     * @return move
     */
    public int getMoveCol() {
        return moveCol;
    }
    /**
     * Getter for choice
     * @return choice
     */
    public String getChoice() {
        return choice;
    }
    
    //-----------------------------------------------
    
    /**
     * Description of player 
     * @return String statement
     */
    public String toString() {
        return getName() + " plays with the " + getMarker() + " marker.";
    }
    
    

}
