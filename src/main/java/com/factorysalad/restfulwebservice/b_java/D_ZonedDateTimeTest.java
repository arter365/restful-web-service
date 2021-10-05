package com.factorysalad.restfulwebservice.b_java;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/*
LocalDateTime과 지역정보(Zone)을 합친 클래스
ISO-8601에서 Asia/Seoul 같은 타임존이 포함된 날짜와 시간(예: 2020-01-21T00:53:30+09:00 Asia/Seoul)을 나타낸다.
 */
public class D_ZonedDateTimeTest {
    public static void main(String[] args) {
        // 현재 날짜 시간 지역
        ZonedDateTime zdt1 = ZonedDateTime.now();   // zone을 지정하지 않으면 서버 지역을 기본으로 가져온다.
        System.out.println(zdt1);                   // yyyy-MM-ddTHH:mm:ss.xxxxxxxxx+09:00[Asia/Seoul] (현재 날짜 시간 지역이 표시됨)

        ZonedDateTime zdt2 = ZonedDateTime.now(ZoneId.of("Australia/Sydney"));
        System.out.println(zdt2);                   // 2021-03-09T14:41:22.851519900+11:00[Australia/Sydney]

        // 지정한 날짜 시간 지역 of
        ZonedDateTime zdt3 = ZonedDateTime.of(LocalDate.of(1945,8,15), LocalTime.of(8,10,12),ZoneId.of("Asia/Seoul"));
        System.out.println(zdt3);                   // 1945-08-15T08:10:12+09:00[Asia/Seoul]

        // 메서드 사용
        System.out.println(zdt1.withZoneSameInstant(ZoneId.of("Australia/Sydney"))); // 서울 Zone으로 시드니의 시간을 알 수 있다.
        System.out.println(zdt3.getMonthValue());   // 8
        System.out.println(zdt3.getZone());         // Asia/Seoul
        // 연산해도 원본 유지
        System.out.println(zdt3.plusDays(50));      // 1945-10-04T08:10:12+09:00[Asia/Seoul]
        System.out.println(zdt3);                   // 1945-08-15T08:10:12+09:00[Asia/Seoul]
    }
}
