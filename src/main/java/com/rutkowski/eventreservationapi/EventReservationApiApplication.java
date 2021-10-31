package com.rutkowski.eventreservationapi;

import com.rutkowski.eventreservationapi.service.SeatingChartsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class EventReservationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventReservationApiApplication.class, args);

//		SeatingChartsService chart = new SeatingChartsService(3, 11);
//
//		try (Scanner scanner = new Scanner(System.in)) {
//			String[] seatLocations = parseInitialReservations(scanner);
//
//			// Fulfill initial reservations, if any.
//			if(!seatLocations[0].equals("")){
//				for(String seatLoc : seatLocations){
//					String[] parsedLoc = seatLoc.split("R|C");
//					chart.reserveSeat(Integer.parseInt(parsedLoc[1]), Integer.parseInt(parsedLoc[2]));
//				}
//			}
//
//			fulfillGroupReservations(chart, scanner);
//		}
//
//		System.out.println(chart.getNumAvailable());
	}

//	/**
//	 * Retrieves the locations of all initial seat reservations for the seating chart, from stdin.
//	 *
//	 * @param scanner   The scanner that will be used to collect the user input.
//	 *                  Should be initialized with System.in input stream.
//	 * @return          Returns an array of all initial reservation locations in String format.
//	 */
//	private static String[] parseInitialReservations(Scanner scanner){
//		String seatLocations;
//
//		while(true){
//			try{
//				seatLocations = scanner.nextLine();
//				if(seatLocations.matches("(R\\d+C\\d+\\s?)+") || seatLocations.matches("")){
//					break;
//				}else{
//					throw new InputMismatchException();
//				}
//			}catch(InputMismatchException e){
//				System.out.println("Error: Enter initial reservations in the form 'R1C1', or hit enter.");
//				seatLocations = "";
//			}
//		}
//		return seatLocations.split(" ");
//	}
//
//	/**
//	 * Retrieves user input for and fulfills group reservation requests, until the user enters an empty newline or '-1'.
//	 *
//	 * @param chart     The SeatingChart object in which group reservations will be fulfilled.
//	 * @param scanner   The Scanner that will be used to collect user input. Should be initialized with System.in input stream.
//	 */
//	private static void fulfillGroupReservations(SeatingChartsService chart, Scanner scanner){
//		while(true){
//			String input;
//			try{
//				input = scanner.nextLine();
//				if(input.matches("\\d+") && !input.equals("-1")){
//					System.out.println(chart.requestGroupReservation(Integer.parseInt(input)));
//				}else if(input.equals("") || input.equals("-1")){
//					break;
//				}else{
//					throw new InputMismatchException();
//				}
//			}catch(InputMismatchException e){
//				System.out.println("Error: Expected integer for group reservation.");
//				throw e;
//			}
//		}
//	}

}
