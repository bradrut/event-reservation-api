package seatingchart;

/**
 *
 * @author Brad Rutkowski
 */
public class Seat {
    
    private boolean available;
    private final int manhattanDistance;
    private final String location;
    
    public Seat(int row, int column, int rowTotal){
        available = true;
        manhattanDistance = calculateManhattanDistance(row, column, rowTotal);
        location = String.format("R%dC%d", row, column);
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
    
    private int calculateManhattanDistance(int row, int column, int rowTotal){
        if(rowTotal%2 != 0 || column > rowTotal/2){
            return Math.abs((((int)rowTotal/2)+1)-column)+row-1;
        }else{
            return ((rowTotal/2)-column)+row-1;
        }
    }
    
    public String getLocation(){
        return location;
    }
    
}
