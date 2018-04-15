package com.tw;

import java.util.HashMap;

public class StudentRecord {

    private String studentName;
    private String id;
    private HashMap<String, Double> scores;


    public StudentRecord(String studentName, String id) {
        this.studentName = studentName;
        this.id = id;
        scores = new HashMap<>();
    }

    public String getStudentName() {
        return studentName;
    }

    public String getId() {
        return id;
    }

    public void addScore(String subject, double score) {

        scores.put(subject, score);

    }

    public HashMap<String, Double> getScores() {
        return scores;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StudentRecord && ((StudentRecord) obj).id.equals(this.id);
    }
}
