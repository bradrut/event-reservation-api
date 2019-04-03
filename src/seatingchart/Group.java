package seatingchart;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents any grouping of Seat objects. Used primarily to keep track of groups
 * of consecutive available seats within a SeatingChart, but can be used to
 * represent any group of seats within a row.
 * 
 * @author Brad Rutkowski
 */
public class Group {
    
    private final ArrayList<Seat> seatGroup;
    private int smallestDistance;
    private final int rowNum;
    
    public Group(List<Seat> group, int rowNum){
        this.seatGroup = new ArrayList<>(group);
        this.rowNum = rowNum;
        setSmallestDistance();
    }
    
    private void setSmallestDistance(){
        int dist = seatGroup.get(0).getDistance();
        for(Seat seat : seatGroup){
            if(seat.getDistance() < dist) dist = seat.getDistance();
        }
        smallestDistance = dist;
    }
    
    int getSmallestDistance(){
        return smallestDistance;
    }
    
    public int getRowNum(){
        return rowNum;
    }
    
    public ArrayList<Seat> getSeatGroup(){
        return this.seatGroup;
    }
    
    /**
     * Calculates whether this group is better than the given group based on
     * smallest Manhattan distance and row number.
     * 
     * @param group The group to compare this group to.
     * @return  Returns true if this group is a better location than the given 
     *          group, and returns false otherwise.
     */
    boolean isBetter(Group group){
        if(smallestDistance < group.getSmallestDistance()){
            return true;
        }else if(smallestDistance == group.getSmallestDistance()){
            return rowNum <= group.getRowNum();
        }
        return false;
    }
    
}
