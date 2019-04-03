package seatingchart;

import java.util.ArrayList;

/**
 *
 * @author Brad Rutkowski
 */
public class Row {
    
    private final ArrayList<Seat> seats;
    private final int rowNum;
    
    public Row(int rowNum, int numSeats){
        seats = new ArrayList<>();
        this.rowNum = rowNum;
        for(int i=0; i<numSeats; i++) seats.add(new Seat(rowNum, i+1, numSeats));
    }
    
    public ArrayList<Seat> getSeats(){
        return seats;
    }
    
    String reserveSeat(int colNum){
        seats.get(colNum-1).setAvailability(false);
        return seats.get(colNum-1).getLocation();
    }
    
    String reserveGroup(int groupSize){
        return null;
    }
    
    int getRowNum(){
        return this.rowNum;
    }
    
}
