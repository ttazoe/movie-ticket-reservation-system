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
    List<List<Seat>> seatMap; // TODO : Theater Class を作ってそっちにデータと機能を移動させる。

    public void showVacantSeats(){
        for (int horizontalRow = 0; horizontalRow < seatMap.size(); horizontalRow++){
            for (int verticalRow = 0; verticalRow < seatMap.get(horizontalRow).size(); verticalRow++){
                if (seatMap.get(horizontalRow).get(verticalRow).isVacant) {
                    System.out.println("horizontalRow : " + horizontalRow + " verticalRow : " + verticalRow);
                }
            }
        }
    }

    public void makeSeatReservation(ReservationDetail reservationDetail){
        seatMap.get(reservationDetail.seatHorizontalIndex).get(reservationDetail.seatVerticalIndex).isVacant = false;
    }

    public void rollbackSeatReservation(ReservationDetail reservationDetail){
        seatMap.get(reservationDetail.seatHorizontalIndex).get(reservationDetail.seatVerticalIndex).isVacant = true;
    }
}
