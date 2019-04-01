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
        
        // TODO: Set delimiter on scanner to take integers.
        while(true){
            // TODO: Take integers to be used for group reservations.
            break;
        }
        
        printer.printAvailability();
    }
    
}
