package seatingchart;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brad Rutkowski
 */
public class SeatingChart {
    
    private final Row[] rows;
    private final int numRows;
    private final int numColumns;
    private int numAvailable;
    private final ArrayList<Group> availableGroups;
    
    public SeatingChart(int numRows, int numColumns){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numAvailable = numRows*numColumns;
        
        rows = new Row[numRows];
        availableGroups = new ArrayList<>();
        
        for(int i=0; i<numRows; i++){
            rows[i] = new Row(i+1, numColumns);
            availableGroups.add(new Group(rows[i].getSeats(), i+1));
        }
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
    
    public ArrayList<Group> getAvailableGroups(){
        return availableGroups;
    }
    
    public boolean checkAvailability(int rowNum, int colNum){
        try{
            return rows[rowNum-1].getSeats().get(colNum-1).available();
        }catch(IndexOutOfBoundsException e){
            System.out.printf("Error: Seat at location R%dC%d does not exist.\n", rowNum, colNum);
            throw e;
        }
    }
    
    String reserveSeat(int rowNum, int colNum){
        Seat seat = rows[rowNum-1].getSeats().get(colNum-1);
        if(!seat.available()) return null;
        
        seat.setAvailability(false);
        numAvailable--;
        
        for(Group group : availableGroups){
            if(group.getSeatGroup().contains(seat)){
                updateAvailableGroups(group);
                break;
            }
        }
        
        return seat.getLocation();
    }
    
    /**
     * Reserves a consecutive number of seats in the best location of the given
     * Group of available seats.
     * 
     * @param   numRequested    The number of seats to be reserved.
     * @param   group           The Group of available seats in which the requested
     *                          reservations will be fulfilled.
     * @return  Returns a list containing the seats that were reserved.
     */
    private List<Seat> reserveGroup(int numRequested, List<Seat> availableGroup, List<Seat> reservedSeats){
        Seat bestSeat = availableGroup.get(0);
        int smallestDist = bestSeat.getDistance();
        List<Seat> reserved = reservedSeats;
        
        for(Seat seat : availableGroup){
            if(seat.getDistance() < smallestDist){
                bestSeat = seat;
                smallestDist = seat.getDistance();
            }
        }
        
        bestSeat.setAvailability(false);
        availableGroup.remove(bestSeat);
        reservedSeats.add(bestSeat);
        
        if(numRequested > 1){
            reserveGroup(numRequested-1, availableGroup, reservedSeats);
        }
        
        return reservedSeats;
    }
    
    String requestGroupReservation(int numRequested){
        Group targetGroup = null;
        List<Seat> reservedSeats = null;
        String startLoc;
        String endLoc;
        
        for(Group group : availableGroups){
            if(group.getSize() >= numRequested){
                targetGroup = group;
                reservedSeats = reserveGroup(numRequested, new ArrayList(targetGroup.getSeatGroup()), new ArrayList());
                break;
            }
        }
        
        if(reservedSeats != null){
            updateAvailableGroups(targetGroup);
            startLoc = reservedSeats.get(0).getLocation();
            endLoc = reservedSeats.get(reservedSeats.size()-1).getLocation();
            return startLoc + " - " + endLoc;
        }else{
            return null;
        }
    }
    
    /**
     * Updates the available groups within a seating chart given a group that
     * contains any number of consecutively reserved seats.
     */
    private void updateAvailableGroups(Group group){
        List<Group> newGroups = group.split();
        availableGroups.remove(group);
        if(!newGroups.get(0).getSeatGroup().isEmpty()) insertGroup(newGroups.get(0), availableGroups);
        if(!newGroups.get(1).getSeatGroup().isEmpty()) insertGroup(newGroups.get(1), availableGroups);
    }
    
    /**
     * Inserts a given group into a specified sorted ArrayList of groups. The 
     * provided targetList of groups should be sorted primarily by smallest 
     * Manhattan distance, and secondarily by row number. The provided group 
     * will be inserted in a manner such that the target List will remain sorted. 
     * 
     * @param   targetList  The list upon which the newGroup will be inserted.
     * @param   newGroup    The group that will be inserted into the targetList.
     */
    private void insertGroup(Group newGroup, ArrayList<Group> targetList){
        int pos;
        for(pos=0; pos<targetList.size(); pos++){
            if(newGroup.isBetter(targetList.get(pos))){
                break;
            }
        }
        targetList.add(pos, newGroup);
    }
    
}
