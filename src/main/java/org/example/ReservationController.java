package org.example;

import java.sql.Array;
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
        int flag = 1;
        List<List<Integer>> reservationSeats = new ArrayList<>();

        while (flag == 1) {
            // 座席の選択を促す
            System.out.println("下記より座席を選択してください");
            screen1.showVacantSeats();

            System.out.println("horizontalRow : ");
            int horizontalRow = scanner.nextInt();
            System.out.println("verticalRow : ");
            int verticalRow = scanner.nextInt();

            System.out.println("選択された座席は下記でよろしいでしょうか。よければ 1、ちがければ 0 を押してください");
            System.out.println("horizontalRow : " + horizontalRow + " verticalRow : " + verticalRow);

            System.out.println("お客様の年齢を入力ください");
            int age = scanner.nextInt();

            System.out.println("シネマシチズン会員ですか？ Yes = 1、No = 0");
            boolean isCinemaCitizen = scanner.nextBoolean();

            int isOk = scanner.nextInt();
            if (isOk == 1) {
                List<Integer> reservationSeat = new ArrayList<>();
                reservationSeat.add(Integer.valueOf(horizontalRow));
                reservationSeat.add(Integer.valueOf(verticalRow));
                reservationSeats.add(reservationSeat);
                screen1.makeSeatReservation(horizontalRow, verticalRow);
            }
            System.out.println("予約を継続する場合は 1、終了する場合は 0 を押してください。");
            flag = scanner.nextInt();
        }

        // TODO : 各座席のお客様属性を選択


        // TODO : 料金計算ロジック
        System.out.println("下記予約内容で承りました。問題なければ 1、間違いがあれば 0 を押してください。");
        int isOk = scanner.nextInt();
        if (isOk == 1) {
            System.out.println("下記座席の予約を確定しました");
            for (List<Integer> seat : reservationSeats) {
                System.out.printf("horizontalRow : %d, verticalRow : %d\n", seat.get(0), seat.get(1));
            }
            // 料金に関する表示
        }
        if (isOk == 0) {
            // TODO : Rollback
        }
    }
}