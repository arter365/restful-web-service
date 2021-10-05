package com.factorysalad.restfulwebservice.b_java;

import java.time.LocalDateTime;

/*
LocalDate와 LocalTime을 합친 클래스
 */
public class C_LocalDateTimeTest {
    public static void main(String[] args) {
        // 현재 날짜 시간
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);                   // yyyy-MM-ddTHH:mm:ss.xxxxx (현재 날짜와 시간이 표시됨)

        // 지정한 날짜 시간 of
        LocalDateTime ldt2 = LocalDateTime.of(1945,8,15,8,10,12);
        System.out.println(ldt2);                   // 1945-08-15T08:10:12

        // 메서드 사용
        System.out.println(ldt2.getMonthValue());   // 8
        System.out.println(ldt2.getMinute());       // 10
        System.out.println(ldt2.getSecond());       // 12
        // 연산해도 원본 유지
        System.out.println(ldt2.plusDays(50));      // 1945-10-04T08:10:12
        System.out.println(ldt2);                   // 1945-08-15T08:10:12
    }
}
