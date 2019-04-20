package seatingchart;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a seating chart containing a specified number of rows and columns.
 * The SeatingChart handles requests for individual or group seat reservations,
 * and keeps track of all available/reserved seats.
 * 
 * @author Brad Rutkowski
 */
public class SeatingChart {
    
    private final Row[] rows;
    private final int numRows;
    private final int numColumns;
    private int numAvailable;
    
    /**
     * ArrayList containing groups of consecutively available seats, sorted by best location.
     */
    private final ArrayList<Group> availableGroups;
    
    /**
     * Constructs a new SeatingChart object given a specified number of rows and columns.
     * 
     * @param numRows       The number of rows of seats that the chart will contain.
     * @param numColumns    The number of columns of seats that the chart will contain.
     */
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
     * Returns an array of all Row objects that this SeatingChart contains.
     * 
     * @return  Returns an array of all Row objects that this SeatingChart contains.
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
    
    int getNumAvailable(){
        return numAvailable;
    }
    
    /**
     * Checks whether a seat at a specified location is available.
     * 
     * @param rowNum    The row number of the requested seat.
     * @param colNum    The column number of the requested seat.
     * @return          Returns True if the seat is available, and False otherwise.
     */
    public boolean checkAvailability(int rowNum, int colNum){
        try{
            return rows[rowNum-1].getSeats().get(colNum-1).available();
        }catch(IndexOutOfBoundsException e){
            System.out.printf("Error: Seat at location R%dC%d does not exist.\n", rowNum, colNum);
            throw e;
        }
    }
    
    /**
     * Requests for a seat at a specified location to be reserved.
     * 
     * @param rowNum    The row number of the requested seat.
     * @param colNum    The column number of the requested seat.
     * @return      Returns the location of the seat that has been reserved (in the form 'R1C1') in String format.
     *              If the requested seat is not available, returns null.
     */
    String reserveSeat(int rowNum, int colNum){
        Seat seat = rows[rowNum-1].getSeats().get(colNum-1);
        if(!seat.available()) return null;
        
        seat.setAvailability(false);
        numAvailable--;
        
        for(Group group : availableGroups){
            if(group.getSeats().contains(seat)){
                updateAvailableGroups(group);
                break;
            }
        }
        
        return seat.getLocation();
    }
    
    /**
     * Given a group of consecutively available seats, reserves a consecutive number of seats 
     * in the best location of that group.
     * 
     * @param numRequested      The number of seats to be reserved.
     * @param availableGroup    The Group of available seats in which the requested reservations will be placed.
     * @return                  Returns a list of the Seat objects that have been reserved.
     */
    private List<Seat> reserveGroup(int numRequested, List<Seat> availableGroup){
        return reserveGroup(numRequested, availableGroup, new ArrayList<Seat>());
    }
    
    /**
     * Recursively reserves a consecutive number of seats in the best location of the provided group.
     * 
     * @param   numRequested    The number of seats to be reserved.
     * @param   availableGroup  The Group of available seats in which the requested
     *                          reservations will be placed.
     * @param   reservedSeats   An empty arrayList of seats. This is needed to
     *                          recursively keep track of each seat that is reserved.
     * 
     * @return  Returns a list containing the seats that were reserved.
     */
    private List<Seat> reserveGroup(int numRequested, List<Seat> availableGroup, List<Seat> reservedSeats){
        Seat bestSeat = availableGroup.get(0);
        int smallestDist = bestSeat.getDistance();
        
        for(Seat seat : availableGroup){
            if(seat.getDistance() < smallestDist){
                bestSeat = seat;
                smallestDist = seat.getDistance();
            }
        }
        
        bestSeat.setAvailability(false);
        availableGroup.remove(bestSeat);
        
        insertSeat(bestSeat, reservedSeats);
        
        if(numRequested > 1){
            reserveGroup(numRequested-1, availableGroup, reservedSeats);
        }
        
        return reservedSeats;
    }
    
    /**
     * Checks whether a requested group of consecutive reservations can be accommodated, and reserves
     * the seats if possible. The group of seats that are reserved will be the best possible available
     * location within the seating chart.
     * 
     * @param numRequested  The number of consecutive seats to be reserved.
     * @return              Returns the seat location or range of seat locations that have been reserved.
     *                      If there is not a group of consecutively available seats that are able to accommodate
     *                      the request, returns "Not available".
     */
    String requestGroupReservation(int numRequested){
        Group targetGroup = null;
        List<Seat> reservedSeats = null;
        String startLoc;
        String endLoc;
        
        for(Group group : availableGroups){
            if(group.getSize() >= numRequested){
                targetGroup = group;
                reservedSeats = reserveGroup(numRequested, new ArrayList<>(targetGroup.getSeats()));
                break;
            }
        }
        
        if(reservedSeats != null){
            updateAvailableGroups(targetGroup);
            numAvailable -= reservedSeats.size();
            
            startLoc = reservedSeats.get(0).getLocation();
            endLoc = reservedSeats.get(numRequested-1).getLocation();
            
            if(reservedSeats.size() > 1){
                return startLoc + " - " + endLoc;
            }else{
                return startLoc;
            }
        }else{
            return "Not available";
        }
    }
    
    /**
     * Updates the list of available groups within this seating chart given a group that
     * contains any number of consecutively reserved seats. This method should be invoked
     * any time a new seat or group of seats are reserved.
     * 
     * @param group A Group object containing one or more seats that have been marked as reserved.
     */
    private void updateAvailableGroups(Group group){
        List<Group> newGroups = group.split();
        availableGroups.remove(group);
        if(!newGroups.get(0).getSeats().isEmpty()) insertGroup(newGroups.get(0), availableGroups);
        if(!newGroups.get(1).getSeats().isEmpty()) insertGroup(newGroups.get(1), availableGroups);
    }
    
    /**
     * Inserts a given group into a specified sorted ArrayList of groups. The 
     * provided targetList of groups should be sorted primarily by smallest 
     * Manhattan distance, and secondarily by row number. The provided group 
     * will be inserted in such a way that the target List will remain sorted. 
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
    
    /**
     * Inserts a given seat into a sorted List of Seats, ordered by colNum. The provided 
     * seat will be inserted into the list in such a way that the list will remain sorted.
     * 
     * @param seat      The seat to be inserted into the list.
     * @param seatList  The sorted list of Seats upon which the provided Seat will be inserted.
     */
    private void insertSeat(Seat seat, List<Seat> seatList){
        int pos;
        for(pos=0; pos<seatList.size(); pos++){
            if(seat.getColNum() < seatList.get(pos).getColNum()){
                break;
            }
        }
        seatList.add(pos, seat);
    }
    
}
