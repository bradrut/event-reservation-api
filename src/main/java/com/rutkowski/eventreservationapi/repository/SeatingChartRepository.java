package com.rutkowski.eventreservationapi.repository;

import com.rutkowski.eventreservationapi.model.SeatingChart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SeatingChartRepository extends MongoRepository<SeatingChart, String> {

    public SeatingChart findByEventId(String eventId);

}
