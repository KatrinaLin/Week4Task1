package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ReportSystemTest {

    ReportSystem reportSystem;

    @Before
    public void setUp() throws Exception {
        reportSystem = new ReportSystem();
    }

    @Test
    public void getSingleRecord() {
        reportSystem.addRecord("Adam, 001, 数学: 97, 语文: 80, 英语: 85, 编程: 99");
        assertTrue(reportSystem.getSingleRecord("001").equals(new StudentRecord("Adam", "001")));
    }

    @Test
    public void getRecords() {

        assertTrue(reportSystem.getRecords() instanceof HashMap);

    }

    @Test
    public void addRecord() {

        StudentRecord lucy = new StudentRecord("Lucy", "002");

        assertTrue(reportSystem.addRecord("Lucy, 002, 数学: 90, 语文: 98, 英语: 90, 编程: 100"));
        assertTrue(reportSystem.addRecord("Alex, 003, 数学: 97, 语文: 80, 英语: 85, 编程: 99"));

        // scores cannot be non-digit
        assertFalse(reportSystem.addRecord("Alexa, 004, 数学: A, 语文: 80, 英语: 85, 编程: 99"));

        assertEquals(reportSystem.getRecords().get("002"), lucy);   // Student records with the same id are considered as the same
        assertFalse(reportSystem.getRecords().get("003").equals(lucy));
    }

    @Test
    public void validateIDs() {
        ReportSystem reportSystem = new ReportSystem();

        String[] validIDs = {"001", "111", "1024", "999"};
        String[] invalidIDs1 = {"a"};
        String[] invalidIDs2 = {"1.01"};
        String[] invalidIDs3 = {"张三"};
        String[] invalidIDs4 = {"Adam"};

        assertTrue(reportSystem.validateIDs(validIDs));
        assertFalse(reportSystem.validateIDs(invalidIDs1));
        assertFalse(reportSystem.validateIDs(invalidIDs2));
        assertFalse(reportSystem.validateIDs(invalidIDs3));
        assertFalse(reportSystem.validateIDs(invalidIDs4));
    }

    @Test
    public void getMedian() {
        ReportSystem reportSystem = new ReportSystem();

        List<Double> list = new ArrayList<>();
        list.add(98.0);
        list.add(80.0);
        list.add(100.0);
        list.add(95.0);

        assertTrue(reportSystem.getMedian(list) == 96.5);
    }
}