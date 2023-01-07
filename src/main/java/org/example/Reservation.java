package org.example;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Reservation {
    int id;
    List<ReservationDetail> reservationDetails;

    Reservation(int id){
        this(1,new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDetails=" + reservationDetails +
                '}';
    }
}
