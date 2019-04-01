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
    
    /**
     * Returns the array of Row objects contained within this SeatingChart. This 
     * method is for use by the SeatingChartPrinter. Any methods in the 'Row' 
     * class that modify data are set to package private, so that objects 
     * outside of the 'seatingchart' can only read from the chart, never modify.
     * 
     * @return  Array of all Row objects contained within the SeatingChart.
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
            return rows[rowNum-1].getSeats().get(colNum-1).available();
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
    
    String requestGroupReservation(int numRequested){
        /*
        * Explore this approach:
        *   Store "available groups" data in "row" objects up-front. Update
        *   the available groups data every time that a seat is reserved or made
        *   available.
        *   More specifically, do the following within the Row class:
        *     - Store an Array(list?) of "available groups".
        *     - Somehow, store the corresponding "smallest distance" seat within each available group.
        *     - When tasked to find the best spot for a group:
        *       - Sort a row's "available groups" in order from least to greatest "smallest distance seats"
        *       - Iterate through this list until found an "available group" that is big enough to accomodate the request
        *       - Compare the row's accommodation avg weight with the found accomodation in each other row
        */
        return null;
    }
    
}
