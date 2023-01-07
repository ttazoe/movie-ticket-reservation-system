package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationController {


    public static void main(String[] args) {

        // TODO : 予約する映画を選択できるようにする。
        SqlSession session = createSqlSession("SqlMapConfig.xml");
        List<Movie> movies = getAllMovie(session);
        Movie slamDunk = getMovieById(session, 1);

        // TODO : 列ごとに座席数を変更できるようにする。
        // TODO : シアターみたいなクラス / DB から引っ張ってこれるようにする。
        List<List<Seat>> seats = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Seat> horizontalRowSeats = new ArrayList<>();
            for (int j = 0 ; j < 3 ; j++) {
                horizontalRowSeats.add(new Seat(true));
            }
            seats.add(horizontalRowSeats);
        }

        Screen screen1 = new Screen(1, LocalDate.of(2023, 1, 7), LocalTime.of(10, 0), slamDunk, seats);
        Scanner scanner = new Scanner(System.in);

        // TODO : ID を動的に取得する。DB から。
        Reservation reservation = new Reservation(1);
        boolean isReservationInputCompleted = false;
        while (! isReservationInputCompleted) {
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
            System.out.println("予約を終了しますか？終了 : true、継続 : false");
            isReservationInputCompleted = scanner.nextBoolean();
        }

        // TODO : 料金計算ロジック
        System.out.println("下記予約内容で承りました。問題なければ true、間違いがあれば false を入力してください。");
        System.out.println(reservation);

        boolean isReservationConfirmed = scanner.nextBoolean();
        if (isReservationConfirmed) {
            System.out.println("予約を確定しました");
            System.out.println(reservation);
        }
        if (!isReservationConfirmed) {
            for (ReservationDetail reservationDetail : reservation.reservationDetails) {
                screen1.rollbackSeatReservation(reservationDetail);
            }
            screen1.showVacantSeats();
        }
    }

    public static SqlSession createSqlSession(String xmlFilePath) {
        try (Reader reader = Resources.getResourceAsReader(xmlFilePath);) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            return sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // TODO : 正しいエラー処理を実装する。Technical Vertification 側に反映する。
    }

    public static Movie getMovieById(SqlSession session, int index) {
        Movie movie = (Movie) session.selectOne("Movie.getById", index);
        return movie;
    }

    public static List<Movie> getAllMovie(SqlSession session) {
        List<Movie> movies = session.selectList("Movie.getAll");
        return movies;
    }
}