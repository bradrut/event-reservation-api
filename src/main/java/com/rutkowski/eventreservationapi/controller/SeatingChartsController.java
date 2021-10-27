package com.rutkowski.eventreservationapi.controller;

import com.rutkowski.eventreservationapi.controller.request.SeatingChartRequest;
import com.rutkowski.eventreservationapi.model.SeatingChart;
import com.rutkowski.eventreservationapi.service.SeatingChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seating-charts")
public class SeatingChartsController {

    private SeatingChartsService seatingChartsService;

    @Autowired
    public SeatingChartsController(SeatingChartsService seatingChartsService){
        this.seatingChartsService = seatingChartsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeatingChart create(@RequestBody SeatingChartRequest seatingChartRequest){
        return seatingChartsService.create(seatingChartRequest);
    }

}
