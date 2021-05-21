package com.books.bankingapp;

import com.books.bankingapp.database.TimeStampConverter;

import org.junit.Test;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test1() {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        Date date = new Date();
        String st = TimeStampConverter.dateToTimestamp(date);
        System.out.println(st);
        System.out.println(TimeStampConverter.fromTimeStamp(st));
    }
}