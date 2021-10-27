package com.rutkowski.eventreservationapi.controller.request;

import lombok.Data;

@Data
public class SeatingChartRequest {

    public String eventId;
    public int numRows;
    public int numColumns;

}
