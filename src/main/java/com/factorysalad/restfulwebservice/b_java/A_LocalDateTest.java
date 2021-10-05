package com.factorysalad.restfulwebservice.b_java;

import java.time.LocalDate;

public class A_LocalDateTest {
    public static void main(String[] args) {
        // 현재 날짜
        LocalDate date1 = LocalDate.now();
        System.out.println(date1);                  // yyyy-MM-dd (현재 날짜가 표시됨)

        // 지정한 날짜 of
        LocalDate date2 = LocalDate.of(1945,8,15);
        System.out.println(date2);                  // 1945-08-15

        // 메서드 사용
        System.out.println(date2.getYear());        // 1945
        System.out.println(date2.getMonthValue());  // 8
        System.out.println(date2.getDayOfMonth());  // 15
        // 연산해도 원본 유지
        System.out.println(date2.plusDays(50));     // 1945-10-04
        System.out.println(date2);                  // 1945-08-15
    }
}
