package com.rutkowski.eventreservationapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group of consecutive Seat objects.
 * 
 * @author Brad Rutkowski
 */
public class Group {
    
    private final ArrayList<Seat> seats;    /** The List of Seats within this group. */
    private final int rowNum;   /** The number of the row in which this Group's seats reside. */
    private final int size;     /** The number of seats within this group. */
    
    /**
     * The Manhattan distance of the seat with the best location within this group. Used to represent
     * the Group's overall location quality, since all Seats within this Group should be consecutive.
     */
    private int smallestDistance;
    
    /**
     * Constructs a new Group object representing a collection of consecutive Seats.
     * 
     * @param seats     A list of consecutive seats which will be stored in this Group.
     * @param rowNum    The number of the row in which this Group's seats reside.
     */
    public Group(List<Seat> seats, int rowNum){
        this.seats = new ArrayList<>(seats);
        this.rowNum = rowNum;
        size = this.seats.size();
        setSmallestDistance();
    }
    
    
    private void setSmallestDistance(){
        if(!seats.isEmpty()){
            int dist = seats.get(0).getDistance();
            for(Seat seat : seats){
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
    
    public int getSize(){
        return size;
    }
    
    public ArrayList<Seat> getSeats(){
        return this.seats;
    }
    
    /**
     * Calculates whether this group is better than another group based on smallest Manhattan distance 
     * and row number. Since the Seats in a Group should be consecutive, the Group with the smallest
     * Manhattan distance is considered better. If both groups have the same smallest distance, the
     * group with the smaller row number is considered better.
     * 
     * @param group     The Group to compare this group to.
     * @return          Returns true if this group holds a better location than the given 
     *                  group, and returns false otherwise.
     */
    public boolean isBetter(Group group){
        if(smallestDistance < group.getSmallestDistance()){
            return true;
        }else if(smallestDistance == group.getSmallestDistance()){
            return rowNum <= group.getRowNum();
        }
        return false;
    }
    
    
    /**
     * Splits this group into two new groups around the seats that are already reserved within the group. 
     * If all reserved seats are at the beginning or end of the Group, the first or second group 
     * returned, respectively, will be of size 0.
     * 
     * @return  Returns an array of groups containing only consecutively available seats.
     */
    public List<Group> split(){
        List<Group> newGroups = new ArrayList<>();
        
        for(int i=0; i<size; i++){
            if(!seats.get(i).available()){
                newGroups.add(new Group(seats.subList(0, i), rowNum));
                break;
            }
        }

        for(int i=size-1; i>=0; i--){
            if(!seats.get(i).available()){
                newGroups.add(new Group(seats.subList(i+1, size), rowNum));
                break;
            }
        }
        
        return newGroups;
    }
    
}
