package org.example.domain;

import org.example.annotation.AfterSuite;
import org.example.annotation.AfterTest;
import org.example.annotation.BeforeSuite;
import org.example.annotation.BeforeTest;
import org.example.annotation.CsvSource;
import org.example.annotation.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class Statistic {
    private static LocalDateTime startTime;

    @BeforeSuite
    public static void printStart() {
        System.out.println("****** Start at " + LocalDateTime.now() + "\n");
    }

    @AfterSuite
    public static void printEnd() {
        System.out.println("\n****** Finish Test at " + LocalDateTime.now());
    }

    @BeforeTest
    public static void printStartTest() {
        startTime = LocalDateTime.now();
        System.out.println("Test Start at " + startTime);
    }

    @AfterTest
    public static void printEndTest() {
        System.out.println("Test passed at " + Duration.between(startTime, LocalDateTime.now()).getNano() + " nanoseconds \n");
        startTime = null;
    }

    @Test(priority = 1)
    public void testMethod() {
        System.out.println("1 test");
    }

    @Test(priority = 4)
    public void testMethod2() {
        System.out.println("2 test");
    }

    @Test(priority = 8)
    public void testMethod8() {
        System.out.println("8 test");
    }

    @CsvSource
    public void testMethod(int a, String b, int c, boolean d) {
        checkCsv(a, b, c, d);
    }

    @CsvSource("10, Java, 10, true")
    public void testMethod2(int a, String b, int c, boolean d) {
        checkCsv(a, b, c, d);
    }

    private void checkCsv(int a, String b, int c, boolean d) {
        if (a == c && d) {
            System.out.println(b);
        } else {
            System.out.println("Fail check params: a == b && d is true");
        }
    }
}
