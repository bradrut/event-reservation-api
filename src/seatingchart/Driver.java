package seatingchart;

import chartprinter.SeatingChartPrinter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Brad Rutkowski
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SeatingChart chart = new SeatingChart(3, 11);
        SeatingChartPrinter printer = new SeatingChartPrinter(chart);
        
        Scanner scanner = new Scanner(System.in);
        
        // Retrieve valid input for initial reservations.
        // Sample input: R1C1 R2C2 R1C6 R2C5 R2C6 R2C11 R3C5
        String seats = "";
        while(true){
            try{
                seats = scanner.nextLine();
                if(seats.matches("(R\\d+C\\d+\\s?)+") || seats.matches("")){
                    break;
                }else{
                    throw new InputMismatchException();
                }
            }catch(InputMismatchException e){
                System.out.println("Error: Enter initial reservations in the form 'R1C1', or hit enter.");
                seats = "";
            }
        }
        
        //Parse initial reservation seat locations and reserve.
        if(!seats.equals("")){
            String[] parsedSeats = seats.split(" ");
            for(String seatLoc : parsedSeats){
                String[] parsedLoc = seatLoc.split("R|C");
                chart.reserveSeat(Integer.parseInt(parsedLoc[1]), Integer.parseInt(parsedLoc[2]));
            }
        }
        
        while(true){
            String input;
            try{
                input = scanner.nextLine();
                if(input.matches("\\d+") && !input.equals("-1")){
                    chart.requestGroupReservation(Integer.parseInt(input));
                }else if(input.equals("") || input.equals("-1")){
                    break;
                }else{
                    throw new InputMismatchException();
                }
            }catch(InputMismatchException e){
                System.out.println("Error: Expected integer for group reservation.");
                throw e;
            }
        }
        
        // TODO: END PROGRAM SEQUENCE (see requirements).
        
        printer.printAvailability();
        printer.printDistances();
    }
    
}
