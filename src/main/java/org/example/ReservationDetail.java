package org.example;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReservationDetail {
    int seatHorizontalIndex;
    int seatVerticalIndex;
    Audience audience;

    @Override
    public String toString() {
        return "ReservationDetail{" +
                "seatHorizontalIndex=" + seatHorizontalIndex +
                ", seatVerticalIndex=" + seatVerticalIndex +
                ", audience=" + audience +
                '}';
    }
}
