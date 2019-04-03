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
    private int size;
    
    public Group(List<Seat> group, int rowNum){
        this.seatGroup = new ArrayList<>(group);
        this.rowNum = rowNum;
        size = seatGroup.size();
        setSmallestDistance();
    }
    
    private void setSmallestDistance(){
        if(!seatGroup.isEmpty()){
            int dist = seatGroup.get(0).getDistance();
            for(Seat seat : seatGroup){
                if(seat.getDistance() < dist) dist = seat.getDistance();
            }
            smallestDistance = dist;
        }else{
            smallestDistance = -1;
        }
    }
    
    int getSmallestDistance(){
        return smallestDistance;
    }
    
    public int getRowNum(){
        return rowNum;
    }
    
    int getSize(){
        return size;
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
    
    
    /**
     * Splits this group into two new groups around the seats that are already
     * reserved. If all reserved seats are at the beginning or end of the Group,
     * the first or second group returned, respectively, will be of size 0. 
     * Note: This method will only work when the reserved seat(s) in this group
     * are consecutive.
     * 
     * @return  An array of groups containing only consecutively available seats.
     */
    List<Group> split(){
        List<Group> newGroups = new ArrayList<>();
        
        for(int i=0; i<size; i++){
            if(!seatGroup.get(i).available()){
                newGroups.add(new Group(seatGroup.subList(0, i), rowNum));
                break;
            }
        }

        for(int i=size-1; i>=0; i--){
            if(!seatGroup.get(i).available()){
                newGroups.add(new Group(seatGroup.subList(i+1, size), rowNum));
                break;
            }
        }
        
        return newGroups;
    }
    
}
