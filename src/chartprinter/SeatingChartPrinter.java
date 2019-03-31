package chartprinter;

import seatingchart.SeatingChart;
import seatingchart.Row;
import seatingchart.Seat;

/**
 *
 * @author Brad Rutkowski
 */
public class SeatingChartPrinter {
    
    private final SeatingChart seatingChart;
    
    public SeatingChartPrinter(SeatingChart chart){
        seatingChart = chart;
    }
    
    public void printAvailability(){
        // Print column numbers
        System.out.print("  ");
        for(int i=0; i<seatingChart.getNumColumns(); i++){
            if(i<9){
                System.out.print(i+1 + "  ");
            }else{
                System.out.print(i+1 + " ");
            }
        }
        System.out.print("\n");
        
        System.out.print("  ");
        for(int i=0; i<(seatingChart.getNumColumns()*3)-2; i++){
            System.out.print("_");
        }
        System.out.print("\n");
        
        // Print availability for each seat
        Row[] rows = seatingChart.getRows();
        for(int i=0; i<rows.length; i++){
            System.out.print(i+1 + "|");
            Seat[] seats = rows[i].getSeats();
            for(int j=0; j<seats.length; j++){
                if(seats[j].available()){
                    System.out.print("o  ");
                }else{
                    System.out.print("x  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void printDistances(){
        for(Row row : seatingChart.getRows()){
            for(Seat seat : row.getSeats()){
                if(seat.getDistance() < 10){
                    System.out.printf("%d  ", seat.getDistance());
                }else{
                    System.out.printf("%d ", seat.getDistance());
                }
            }
            System.out.println();
        }
    }
    
}
