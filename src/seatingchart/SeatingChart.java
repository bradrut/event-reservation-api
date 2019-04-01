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
    
    /*
    *  This method is for use by the SeatingChartPrinter. Any methods in the
    *  'Row' class that modify data are set to package private, so that objects
    *  outside of the 'seatingchart' can only read from the chart, never modify.
    */
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
    public boolean checkAvailability(int rowNum, int colNum){
        try{
            return rows[rowNum-1].getSeats()[colNum-1].available();
        }catch(IndexOutOfBoundsException e){
            System.out.printf("Error: Seat at location R%dC%d does not exist.\n", rowNum, colNum);
            throw e;
        }
    }
    
    String reserveSeat(int rowNum, int colNum){
        if(checkAvailability(rowNum, colNum)){
            numAvailable--;
            return rows[rowNum-1].reserveSeat(colNum);
        }else{
            return null;
        }
    }
    
    boolean makeInitialReservations(String seatLocations){
        return false;
    }
    
    String requestGroupReservation(int numSeats){
        return null;
    }
    
}
