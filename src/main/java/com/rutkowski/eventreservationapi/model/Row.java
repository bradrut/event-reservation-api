package com.rutkowski.eventreservationapi.model;

import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.ArrayList;

/**
 * Represents a single Row within a SeatingChart which contains a specified number of consecutive seats.
 * 
 * @author Brad Rutkowski
 */
@Data
public class Row {

    private final ArrayList<Seat> seats;

    private final int rowNum;

    private final int numSeats;
    
    /**
     * Constructs a new Row object containing a specified number of seats.
     * 
     * @param rowNum    The number of the row that this object will represent within the SeatingChart.
     * @param numSeats  The number of seats that this row will contain.
     */
    public Row(int rowNum, int numSeats){
        this.rowNum = rowNum;
        this.numSeats = numSeats;
        seats = new ArrayList<>();
        for(int i=0; i<numSeats; i++) seats.add(new Seat(rowNum, i+1, numSeats));
    }


    @PersistenceConstructor
    private Row(int rowNum, int numSeats, ArrayList<Seat> seats){
        this.rowNum = rowNum;
        this.numSeats = numSeats;
        this.seats = seats;
    }
    
}
