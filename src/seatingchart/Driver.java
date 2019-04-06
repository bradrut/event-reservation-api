package seatingchart;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Brad Rutkowski
 */
public class Driver {
    
    private static String[] parseInitialReservations(Scanner scanner){
        String seatLocations;
        
        while(true){
            try{
                seatLocations = scanner.nextLine();
                if(seatLocations.matches("(R\\d+C\\d+\\s?)+") || seatLocations.matches("")){
                    break;
                }else{
                    throw new InputMismatchException();
                }
            }catch(InputMismatchException e){
                System.out.println("Error: Enter initial reservations in the form 'R1C1', or hit enter.");
                seatLocations = "";
            }
        }
        return seatLocations.split(" ");
    }
    
    private static void fulfillGroupReservations(SeatingChart chart, Scanner scanner){
        while(true){
            String input;
            try{
                input = scanner.nextLine();
                if(input.matches("\\d+") && !input.equals("-1")){
                    System.out.println(chart.requestGroupReservation(Integer.parseInt(input)));
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
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SeatingChart chart = new SeatingChart(3, 11);
        
        try (Scanner scanner = new Scanner(System.in)) {
            String[] seatLocations = parseInitialReservations(scanner);
            
            // Fulfill initial reservations, if any.
            if(!seatLocations[0].equals("")){
                for(String seatLoc : seatLocations){
                    String[] parsedLoc = seatLoc.split("R|C");
                    chart.reserveSeat(Integer.parseInt(parsedLoc[1]), Integer.parseInt(parsedLoc[2]));
                }
            }
            
            fulfillGroupReservations(chart, scanner);
        }
        
        System.out.println(chart.getNumAvailable());
    }
    
}
