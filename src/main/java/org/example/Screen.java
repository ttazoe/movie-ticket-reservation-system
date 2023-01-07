package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Data
public class Screen {
    int id;
    LocalDate date;
    LocalTime time;
    Movie movie;
    Seat[][] seatMap = new Seat[3][3];

    public void showVacantSeats(){
        for (int horizontalRow = 0; horizontalRow < seatMap.length; horizontalRow++){
            for (int verticalRow = 0; verticalRow < seatMap[horizontalRow].length; verticalRow++){
                if (seatMap[horizontalRow][verticalRow].isVacant) {
                    System.out.println("horizontalRow : " + horizontalRow + " verticalRow : " + verticalRow);
                }
            }
        }
    }

    public void makeSeatReservation(ReservationDetail reservationDetail){
        seatMap[reservationDetail.seatHorizontalIndex][reservationDetail.seatVerticalIndex].isVacant = false;
    }

    public void rollbackSeatReservation(ReservationDetail reservationDetail){
        seatMap[reservationDetail.seatHorizontalIndex][reservationDetail.seatVerticalIndex].isVacant = true;
    }
}
