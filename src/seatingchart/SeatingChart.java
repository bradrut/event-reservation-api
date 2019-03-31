package seatingchart;

/**
 *
 * @author Brad Rutkowski
 */
public class SeatingChart {
    
    private final Row[] rows;
    private final int numRows;
    private final int numColumns;
    private int numAvailable;
    
    public SeatingChart(int numRows, int numColumns){
        this.numRows = numRows;
        this.numColumns = numColumns;
        rows = new Row[numRows];
        for(int i=0; i<numRows; i++) rows[i] = new Row(i+1, numColumns);
        numAvailable = numRows*numColumns;
    }
    
    public Row[] getRows(){
        return rows;
    }
    
    public int getNumRows(){
        return numRows;
    }
    
    public int getNumColumns(){
        return numColumns;
    }
    
    /*
    * 
    */
    public boolean checkAvailability(int row, int column){
        return false;
    }
    
    String reserveSeat(int row, int column){
        return rows[row-1].reserveSeat(column);
    }
    
    boolean makeInitialReservations(String seatLocations){
        return false;
    }
    
    String requestGroupReservation(int numSeats){
        return null;
    }
    
}
