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
        if(checkAvailability(rowNum, colNum)){
            String reservedSeatLoc = rows[rowNum-1].reserveSeat(colNum);
            numAvailable--;
            updateAvailableGroups(rows[rowNum-1].getSeats().get(colNum-1));
            return reservedSeatLoc;
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
    
    /**
     * Updates the available groups within a SeatingChart given a newly reserved
     * seat.
     * 
     * @param seat 
     */
    private void updateAvailableGroups(Seat seat){
        Group oldGroup = null;
        Group newGroup1 = null;
        Group newGroup2 = null;
        
        for(Group group : availableGroups){
            if(group.getSeatGroup().contains(seat)){
                oldGroup = group;
                ArrayList<Seat> oldSeatGroup = group.getSeatGroup();
                int reservedSeatIndex = oldSeatGroup.indexOf(seat);
                int rowNum = group.getRowNum();
                
                // Split the old group into two groups around the newly reserved seat.
                List<Seat> subList1 = oldSeatGroup.subList(0, reservedSeatIndex);
                List<Seat> subList2 = oldSeatGroup.subList(reservedSeatIndex+1, oldSeatGroup.size());
                
                if(!subList1.isEmpty()) newGroup1 = new Group(subList1, rowNum);
                if(!subList2.isEmpty()) newGroup2 = new Group(subList2, rowNum);
                
                break;
            }
        }
        availableGroups.remove(oldGroup);
        if(newGroup1 != null) insertGroup(newGroup1, availableGroups);
        if(newGroup2 != null) insertGroup(newGroup2, availableGroups);
        System.out.println(availableGroups.size());
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
