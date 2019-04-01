package seatingchart;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brad Rutkowski
 */
public class Row {
    
    private final List<Seat> seats;
    private List<Group> availableGroups;
    private int numAvailable;
    private final int rowNum;
    
    public Row(int rowNum, int numSeats){
        seats = new ArrayList<>();
        numAvailable = numSeats;
        this.rowNum = rowNum;
        for(int i=0; i<numSeats; i++) seats.add(new Seat(rowNum, i+1, numSeats));
    }
    
    public List<Seat> getSeats(){
        return seats;
    }
    
    int getNumAvailable(){
        return numAvailable;
    }
    
    String reserveSeat(int colNum){
        seats.get(colNum-1).setAvailability(false);
        numAvailable--;
        return seats.get(colNum-1).getLocation();
    }
    
    String reserveGroup(int groupSize){
        return null;
    }
    
}
