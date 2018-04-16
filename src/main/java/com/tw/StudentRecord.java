package com.tw;

import java.util.HashMap;

public class StudentRecord {

    private String studentName;
    private String id;
    private HashMap<String, Double> scoreRecord; // the key is id, not studentName


    public StudentRecord(String studentName, String id) {
        this.studentName = studentName;
        this.id = id;
        scoreRecord = new HashMap<>();
    }

    public String getStudentName() {
        return studentName;
    }

    public String getId() {
        return id;
    }

    public void addScore(String subject, double score) {
        scoreRecord.put(subject, score);
    }

    public HashMap<String, Double> getScoreRecord() {
        return scoreRecord;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StudentRecord && ((StudentRecord) obj).id.equals(this.id);
    }

    public double listScores(String[] subjects) {
        System.out.print(studentName);

        double total = 0;

        for (String s : subjects) {
            double score = scoreRecord.get(s);
            System.out.print("|" + score);
            total += score;
        }

        System.out.printf("|%.2f", total / subjects.length);    // print student's average scores
        System.out.print("|" + total);  // print student's total scores

        System.out.println();

        return total;
    }
}
