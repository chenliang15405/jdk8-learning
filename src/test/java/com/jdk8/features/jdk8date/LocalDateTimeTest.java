package com.jdk8.features.jdk8date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 *  LocalDate   LocalTime   LocalDateTime
 *
 *  Instant 时间戳，从19700101000000  到某个时间的毫秒数
 *
 *  Duration 两个“时间”之间的间隔
 *
 *  Period 两个“日期” 之间的间隔
 *
 *  TemporalAdjuster 时间校正器
 *      TemporalAdjusters  该类通过静态方法提供了大量的常用TemporalAdjuster的实现
 *
 *  DateTimeFormatter  格式化时间/日期
 *
 *  ZonedDate    ZonedTime   ZonedDateTIme   时区
 *
 */
public class LocalDateTimeTest {

    @Test
    public void test1() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime of = LocalDateTime.of(2019, 10, 1, 1, 15, 15);
        System.out.println(of);

        System.out.println(now.getYear());
        System.out.println(now.getMonth());
    }

    @Test
    public void test2(){
        Instant now = Instant.now();  // 默认获取的UTC时区
        System.out.println(now);

        OffsetDateTime offset = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offset);

        System.out.println(now.toEpochMilli()); //毫秒值

        Instant instant = Instant.ofEpochSecond(60); //相对于1970的加毫秒数的时间值
        System.out.println(instant);
    }

    @Test
    public void test3() throws InterruptedException {
        Instant now = Instant.now();

        Thread.sleep(2000);

        Instant now1 = Instant.now();

        Duration between = Duration.between(now, now1);
        System.out.println(between.toMillis());
    }

    @Test
    public void test4() {
        LocalDateTime ldt = LocalDateTime.now();

        LocalDateTime l1 = ldt.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(l1);

        LocalDateTime with = ldt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println(with);

        // 自定义接口实现
        LocalDateTime localDateTime = ldt.with((l) -> {
            LocalDateTime ldt3 = (LocalDateTime) l;

            DayOfWeek dayOfWeek = ldt3.getDayOfWeek();

            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt3.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt3.plusDays(2);
            } else {
                return ldt3.plusDays(1);
            }
        });

        System.out.println(localDateTime);
    }

    @Test
    public void test5(){
        DateTimeFormatter isoDate = DateTimeFormatter.ISO_DATE;
        LocalDateTime now = LocalDateTime.now();

        System.out.println(now.format(isoDate));

        DateTimeFormatter par = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(now.format(par));

    }

    @Test
    public void test6(){
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter par = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(now.parse("2019-01-01 11:12:32",par));
    }

    @Test
    public void test7() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
        System.out.println(now);



    }



}
