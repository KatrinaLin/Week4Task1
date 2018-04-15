package com.tw;

import java.util.HashMap;

public class Record {

    private String name;
    private int id;
    private HashMap<String, Double> scores;


    public Record(String name, int id) {
        this.name = name;
        this.id = id;
        scores = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addScore(String subject, double score) {

        scores.put(subject, score);

    }

    public HashMap<String, Double> getScores() {
        return scores;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Record && this.id == ((Record) obj).id;
    }
}
