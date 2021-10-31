package com.rutkowski.eventreservationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document
public class SeatingChart {

    @Id
    private final String eventId;

    private final int numRows;

    private final int numColumns;

    private int numAvailable;

    @JsonIgnore
    private final Row[] rows;

    /** ArrayList containing groups of consecutively available seats, sorted by best location. */
    @JsonIgnore
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

    @PersistenceConstructor
    private SeatingChart(int numRows, int numColumns, String eventId, Row[] rows, ArrayList<Group> availableGroups){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numAvailable = numRows*numColumns;
        this.eventId = eventId;
        this.rows = rows;
        this.availableGroups = availableGroups;
    }

}
