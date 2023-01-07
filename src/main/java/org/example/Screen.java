package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class Screen {
    int id;
    LocalDate date;
    LocalTime time;
    Movie movie;
    Seat[][] seats = new Seat[3][3];

    public void showVacantSeats(){
        for (int horizontalRow = 0; horizontalRow < seats.length; horizontalRow++){
            for (int verticalRow = 0; verticalRow < seats[horizontalRow].length; verticalRow++){
                if (seats[horizontalRow][verticalRow].isVacant) {
                    System.out.println("horizontalRow : " + horizontalRow + " verticalRow : " + verticalRow);
                }
            }
        }
    }

    public void makeSeatReservation(int horizontalRow, int verticalRow){
        seats[horizontalRow][verticalRow].isVacant = false;
    }

}
