package seatingchart;

/**
 * Represents a single seat within a SeatingChart.
 * 
 * @author Brad Rutkowski
 */
public class Seat {
    
    private boolean available;
    private final int colNum;
    
    /**
     * This seat's Manhattan distance from the front and center seat in the seating chart.
     */
    private final int manhattanDistance;
    
    /**
     * This seat's location in String format (for example, 'R1C1').
     */
    private final String location;
    
    /**
     * Constructs a new seat object representing a seat at the specified row and column in the SeatingChart.
     * 
     * @param rowNum    The number of the row in which this seat is located.
     * @param colNum    The number of the column in which this seat is located.
     * @param rowTotal  The total number of seats in this seat's row. Used to calculate this seat's Manhattan distance.
     */
    public Seat(int rowNum, int colNum, int rowTotal){
        available = true;
        this.colNum = colNum;
        manhattanDistance = calculateManhattanDistance(rowNum, colNum, rowTotal);
        location = String.format("R%dC%d", rowNum, colNum);
    }
    
    public boolean available(){
        return available;
    }
    
    void setAvailability(boolean available){
        this.available = available;
    }
    
    public int getDistance(){
        return manhattanDistance;
    }
    
    int getColNum(){
        return colNum;
    }
    
    /**
     * Calculates this seat's distance from the front and center seat in the SeatingChart.
     * 
     * @param row       The row number of this seat.
     * @param column    The column number of this seat.
     * @param rowTotal  The total number of seats in this seat's row.
     * @return          Returns this Seat's Manhattan distance.
     */
    private int calculateManhattanDistance(int row, int column, int rowTotal){
        if(rowTotal%2 != 0 || column > rowTotal/2){
            return Math.abs((((int)rowTotal/2)+1)-column)+row-1;
        }else{
            return ((rowTotal/2)-column)+row-1;
        }
    }
    
    /**
     * Get this seat's location in String format ('R1C1').
     * 
     * @return  Returns this seat's location in String format.
     */
    public String getLocation(){
        return location;
    }
    
}
