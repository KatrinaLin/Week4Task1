package com.tw;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentRecordTest {

    StudentRecord Adam;

    @Before
    public void setUp() throws Exception {
        Adam = new StudentRecord("Adam", "1001");

        Adam.addScore("数学", 95);
        Adam.addScore("语文", 99);
        Adam.addScore("英语", 97);
        Adam.addScore("编程", 96);
    }

    @Test
    public void getStudentName() {
        assertEquals("Adam", Adam.getStudentName());
    }

    @Test
    public void getId() {
        assertEquals("1001", Adam.getId());
    }

    @Test
    public void addScore() {


    }

    @Test
    public void getScores() {

        assertTrue(Adam.getScoreRecord().get("数学") == 95.0);
        assertTrue(Adam.getScoreRecord().get("语文") == 99.0);
        assertTrue(Adam.getScoreRecord().get("英语") == 97.0);
        assertFalse( Adam.getScoreRecord().get("编程") == 60.0);
    }

    @Test
    public void equals() {
        StudentRecord Amy = new StudentRecord("Amy", "1002");

        StudentRecord Adam2 = new StudentRecord("Adam", "1001");

        StudentRecord AdamChangedName = new StudentRecord("Adam Lee", "1001");

        assertFalse(Adam.equals(Amy));
        assertTrue(Adam.equals(Adam2));
        assertTrue(Adam.equals(AdamChangedName));

    }
}