package com.rutkowski.eventreservationapi.model;

import java.util.ArrayList;

/**
 * Represents a single Row within a SeatingChart which contains a specified number of consecutive seats.
 * 
 * @author Brad Rutkowski
 */
public class Row {
    
    private final ArrayList<Seat> seats;
    private final int rowNum;
    
    /**
     * Constructs a new Row object containing a specified number of seats.
     * 
     * @param rowNum    The number of the row that this object will represent within the SeatingChart.
     * @param numSeats  The number of seats that this row will contain.
     */
    public Row(int rowNum, int numSeats){
        seats = new ArrayList<>();
        this.rowNum = rowNum;
        for(int i=0; i<numSeats; i++) seats.add(new Seat(rowNum, i+1, numSeats));
    }
    
    public ArrayList<Seat> getSeats(){
        return seats;
    }
    
    int getRowNum(){
        return this.rowNum;
    }
    
}
