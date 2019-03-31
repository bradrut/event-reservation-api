package seatingchart;

import chartprinter.SeatingChartPrinter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        
        /*
        Scanner scanner = new Scanner(System.in);
        try(scanner){
            System.out.print("Enter a single seat to reserve: ");
            String seat = scanner.next(Pattern.compile("R\\d+C\\d+"));
            System.out.println("\n" + seat);
        }catch(InputMismatchException e){
            System.out.println(e.);
        }
        */
        
        chart.reserveSeat(1, 5);
        chart.reserveSeat(1, 6);
        chart.reserveSeat(1, 11);
        chart.reserveSeat(2, 4);
        chart.reserveSeat(2, 12);
        printer.printAvailability();
    }
    
}
