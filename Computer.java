public class Computer {
    //~ Fields ................................................................
    private String marker;
    private String name = "Computer";
    private String choice;
    private int moveRow;
    private int moveCol;

    public Computer() {
    }
    //~Public  Methods ........................................................
    public String getMarker() {
        return marker;
    }
    
    public String getName() {
        return name;
    }
    
    public String getChoice() {
        return choice;
    }
    public int getMoveRow() {
        return moveRow;
    }
    public int getMoveCol() {
        return moveCol;
    }
    //----------------------
    
    public void setMarker(String marker) {
        this.marker = marker;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setChoice(String choice) {
        this.choice = choice;
    }
    
    public void setMoveRow(int row) {
        this.moveRow = row;
    }
    
    public void setMoveCol(int col) {
        this.moveCol = col;
    }
    //---------------------------------------
    public String toString() {
        return "Computer plays the (" + getMarker() + ") marker.";
    }
    
    
}

