package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationController {
    public static void main(String[] args) {
        // Initial setups
        Movie slamDunk = new Movie(1, "Slum Dunk");
        Movie harryPotter = new Movie(2, "Harry Potter");

        Seat[][] seats = new Seat[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                seats[i][j] = new Seat(true);
            }
        }

        Screen screen1 = new Screen(1, LocalDate.of(2023, 1, 7), LocalTime.of(10, 0), slamDunk, seats);
//        Screen screen2 = new Screen(2, LocalDate.of(2023,1,7), LocalTime.of(21,0),slamDunk, seats);
//        Screen screen3 = new Screen(3, LocalDate.of(2023,1,9), LocalTime.of(12,0),harryPotter, seats);


        Scanner scanner = new Scanner(System.in);
        // TODO : Screen を選択する処理、今回は screen1 で処理を進める。

        //
        Reservation reservation = new Reservation(1);
        int flag = 1;
        while (flag == 1) {
            // 座席の選択を促す
            System.out.println("下記より座席を選択してください");
            screen1.showVacantSeats();

            System.out.println("horizontalRow : ");
            int horizontalRow = scanner.nextInt();
            System.out.println("verticalRow : ");
            int verticalRow = scanner.nextInt();

//            System.out.println("お客様の名前を入力ください");
//            String name = scanner.next();
//
//            System.out.println("お客様の年齢を入力ください");
//            int age = scanner.nextInt();
//
//            System.out.println("シネマシチズン会員ですか？ true/false");
//            boolean isCinemaCitizen = scanner.nextBoolean();

            String name = "tazoe";
            int age = 33;
            boolean isCinemaCitizen = true;

            Audience audience = new Audience(name, age, isCinemaCitizen);

            ReservationDetail reservationDetail = new ReservationDetail(horizontalRow, verticalRow, audience);
            reservation.reservationDetails.add(reservationDetail);
            screen1.makeSeatReservation(reservationDetail); 
            System.out.println("予約内容に追加しました。");
            System.out.println(reservationDetail);
            System.out.println("予約を継続する場合は 1、終了する場合は 0 を押してください。");
            flag = scanner.nextInt();
        }

        // TODO : 料金計算ロジック
        System.out.println("下記予約内容で承りました。問題なければ 1、間違いがあれば 0 を押してください。");
        System.out.println(reservation);


        int isOk = scanner.nextInt();
        if (isOk == 1) {
            System.out.println("予約を確定しました");
            System.out.println(reservation);
        }
        if (isOk == 0) {
            for (ReservationDetail reservationDetail : reservation.reservationDetails) {
                screen1.rollbackSeatReservation(reservationDetail);
            }
            screen1.showVacantSeats();
        }
    }
}