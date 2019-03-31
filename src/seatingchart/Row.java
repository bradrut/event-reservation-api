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
        try{
            if(seats[colNum-1].available()){
                seats[colNum-1].setAvailability(false);
                numAvailable--;
                return seats[colNum-1].getLocation();
            }else{
                return "Seat at " + seats[colNum-1].getLocation() + " not available.";
            }
        }catch(IndexOutOfBoundsException e){
            System.out.printf("Error: Seat at R%dC%d does not exist.\n\n", rowNum, colNum);
            return null;
        }
    }
    
    String reserveGroup(int groupSize){
        return null;
    }
    
}
