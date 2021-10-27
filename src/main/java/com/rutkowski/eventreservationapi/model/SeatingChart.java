package com.rutkowski.eventreservationapi.model;

import lombok.Data;
import org.hibernate.id.GUIDGenerator;

import java.util.ArrayList;

@Data
public class SeatingChart {

    private final String eventId;
    private final int numRows;
    private final int numColumns;
    private int numAvailable;
    private final Row[] rows;

    /** ArrayList containing groups of consecutively available seats, sorted by best location. */
    // TODO: It might make more sense to move this to the SeatingChartService to be maintained at runtime...
    //  Or would it be OK to persist this?
    private final ArrayList<Group> availableGroups;

    public SeatingChart(int numRows, int numColumns, String eventId){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numAvailable = numRows*numColumns;
        this.eventId = eventId;

        rows = new Row[numRows];
        availableGroups = new ArrayList<>();

        for(int i=0; i<numRows; i++){
            rows[i] = new Row(i+1, numColumns);
            availableGroups.add(new Group(rows[i].getSeats(), i+1));
        }
    }

}
