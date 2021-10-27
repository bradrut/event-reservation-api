package com.rutkowski.eventreservationapi;

import static org.junit.jupiter.api.Assertions.*;

import com.rutkowski.eventreservationapi.service.SeatingChartsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeatingChartsServiceTest {
	
//	SeatingChartsService testChart;
//
//	@Test
//	@BeforeEach
//	void initEmptyChart() {
//		testChart = new SeatingChartsService(4, 12);
//	}
//
//	@Test
//	@DisplayName("Chart should fulfill initial reservations.")
//	void chartShouldFulfillInitialReservations() {
//		assertEquals("R1C5", testChart.reserveSeat(1, 5));
//		assertEquals("R2C6", testChart.reserveSeat(2, 6));
//		assertEquals("R3C7", testChart.reserveSeat(3, 7));
//		assertEquals("R4C8", testChart.reserveSeat(4, 8));
//	}
//
//	@Test
//	@DisplayName("Chart should avoid duplicate initial reservations.")
//	void chartShouldAvoidDuplicateInitialReservations() {
//		assertEquals("R1C5", testChart.reserveSeat(1, 5));
//		assertEquals("R1C6", testChart.reserveSeat(1, 6));
//		assertEquals("Seat 5 in row 1 is already taken.", testChart.reserveSeat(1, 5));
//	}
//
//	@Test
//	@DisplayName("Chart should notify of out of bound seat requests.")
//	void chartShouldNotifyOfOutOfBoundSeatRequests() {
//		// This should ideally be validated client side.
//		assertEquals("Error: Row 0 does not exist.", testChart.reserveSeat(0, 6));
//		assertEquals("Error: Row 5 does not exist.", testChart.reserveSeat(5, 6));
//		assertEquals("Error: Seat 0 in row 1 does not exist.", testChart.reserveSeat(1, 0));
//		assertEquals("Error: Seat 13 in row 1 does not exist.", testChart.reserveSeat(1, 13));
//	}
//
//	@Test
//	@DisplayName("Chart should reserve the best seat group.")
//	void chartShouldReserveBestSeatGroup() {
//		testChart.reserveSeat(1, 6);
//		testChart.reserveSeat(1, 7);
//		testChart.reserveSeat(1, 8);
//		assertEquals("R1C3 - R1C5", testChart.requestGroupReservation(3));
//		assertEquals("R2C6 - R2C7", testChart.requestGroupReservation(2));
//		assertEquals("R1C9", testChart.requestGroupReservation(1));
//		assertEquals("R3C1 - R3C12", testChart.requestGroupReservation(12));
//	}
//
//	@Test
//	@DisplayName("Chart should notify if there is no available group of the requested size.")
//	void chartShouldNotifyIfRequestedGroupNotAvailable() {
//		assertEquals("A group of the requested size is not available.", testChart.requestGroupReservation(13));
//	}
//
//	@Test
//	@Disabled
//	void chartShouldHandleInvalidInputForInitialReservations() {
//		// Input validation will be handled on the front end.
//	}
//
//	@Test
//	@Disabled
//	void chartShouldHandleInvalidInputForGroupRequests() {
//		// Input validation will be handled on the front end.
//	}
//
//	@Test
//	@DisplayName("Chart should keep track of the number of available seats.")
//	void chartShouldKeepTrackOfNumAvailableSeats() {
//		testChart.requestGroupReservation(6);
//		testChart.requestGroupReservation(2);
//		testChart.requestGroupReservation(5);
//		assertEquals(35, testChart.getNumAvailable());
//	}

}
