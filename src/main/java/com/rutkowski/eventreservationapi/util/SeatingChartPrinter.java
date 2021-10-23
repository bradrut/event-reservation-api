package com.rutkowski.eventreservationapi.util;

import com.rutkowski.eventreservationapi.model.Row;
import com.rutkowski.eventreservationapi.model.Seat;
import com.rutkowski.eventreservationapi.service.SeatingChart;

import java.util.List;

/**
 *
 * @author Brad Rutkowski
 */
public class SeatingChartPrinter {
    
    private final SeatingChart seatingChart;
    
    public SeatingChartPrinter(SeatingChart chart){
        seatingChart = chart;
    }
    
    private void printColumnNumbers(){
        System.out.print("   ");
        for(int i=0; i<seatingChart.getNumColumns(); i++){
            if(i<9){
                System.out.print(i+1 + "  ");
            }else{
                System.out.print(i+1 + " ");
            }
        }
        System.out.print("\n");
        
        System.out.print("  ");
        for(int i=0; i<seatingChart.getNumColumns()*3; i++){
            System.out.print("_");
        }
        System.out.print("\n");
    }
    
    public void printAvailability(){
        this.printColumnNumbers();
        
        // Print availability for each seat
        Row[] rows = seatingChart.getRows();
        for(int i=0; i<rows.length; i++){
            System.out.print(i+1 + "| ");
            List<Seat> seats = rows[i].getSeats();
            for(Seat seat : seats){
                if(seat.available()){
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
        this.printColumnNumbers();
        
        Row[] rows = seatingChart.getRows();
        for(int i=0; i<rows.length; i++){
            System.out.print(i+1 + "| ");
            for(Seat seat : rows[i].getSeats()){
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
