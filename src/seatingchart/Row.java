package seatingchart;

/**
 *
 * @author Brad Rutkowski
 */
public class Row {
    
    private final Seat[] seats;
    private int numAvailable;
    private final int rowNum;
    
    public Row(int rowNum, int numSeats){
        seats = new Seat[numSeats];
        numAvailable = numSeats;
        this.rowNum = rowNum;
        for(int i=0; i<numSeats; i++) seats[i] = new Seat(rowNum, i+1, numSeats);
    }
    
    public Seat[] getSeats(){
        return seats;
    }
    
    String reserveSeat(int colNum){
        seats[colNum-1].setAvailability(false);
        numAvailable--;
        return seats[colNum-1].getLocation();
    }
    
    String reserveGroup(int groupSize){
        return null;
    }
    
}
