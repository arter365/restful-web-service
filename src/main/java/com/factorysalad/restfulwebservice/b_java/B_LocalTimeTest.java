package com.factorysalad.restfulwebservice.b_java;

import java.time.LocalTime;

public class B_LocalTimeTest {
    public static void main(String[] args) {
        // 현재 시간
        LocalTime time1 = LocalTime.now();
        System.out.println(time1);                  // HH:mm:ss (현재 시간이 표시됨)

        // 지정한 시간 of
        LocalTime time2 = LocalTime.of(8,10,12);
        System.out.println(time2);                  // 08:10:12

        // 메서드 사용
        System.out.println(time2.getHour());        // 8
        System.out.println(time2.getMinute());      // 10
        System.out.println(time2.getSecond());      // 12
        // 연산해도 원본 유지
        System.out.println(time2.minusHours(20));   // 12:10:12
        System.out.println(time2);                  // 08:10:12
    }
}
