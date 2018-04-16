package com.tw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReportSystem {

    private HashMap<String, StudentRecord> records;
    private String[] subjects = {"数学", "语文", "英语", "编程"};

    public ReportSystem() {
        records = new HashMap<String, StudentRecord>();

    }

    public StudentRecord getSingleRecord(int studentID) {
        return records.get(studentID);
    }

    /*
        Assume that the system only accept input that strictly follows the format:
        "姓名, 学号, 数学: 成绩, 语文: 成绩, 英语: 成绩, 编程: 成绩"
     */
    public boolean addRecord(String input) {

        String[] entries = input.split(", ");

        if (entries.length != 2 + subjects.length) {
            return false;
        }

        String name = entries[0].trim();
        String id = entries[1].trim();

        // check if a student record with the same id already exists
        StudentRecord cur = records.get(id);

        if (cur == null) {
            cur = new StudentRecord(name, id); // instantiate a StudentRecord object if the student record does not exist
        }

        for (int i = 2; i < entries.length; i++) {
            String[] str = entries[i].split(": ");

            if (str.length != 2) {  // each pair should be "学科: 成绩"
                return false;
            }

            cur.addScore(str[0].trim(), Double.parseDouble(str[1].trim()));
        }

        System.out.println("Success!");
        records.put(id, cur);

        return true;
    }

    public boolean printRecords(String input) {

        List<String> studentIDs = Arrays.asList(input.split(", "));

        List<Double> results = new ArrayList<>();

        System.out.println("成绩单\n 姓名|数学|语文|英语|编程|平均分|总分\n========================");

        studentIDs.stream()
                .forEach(id -> {
                    // 如果我们输入的学号不存在，该学号在计算时就会被忽略。
                    if (records.get(id) != null)
                        results.add(printScores(records.get(id)));
                });

        System.out.println("========================");

        System.out.println("全班总分平均数：" + results.stream().mapToDouble(x -> x).average().getAsDouble());

        int size = results.size();
        double median;


        System.out.println("全班总分中位数：" + results.stream().mapToDouble(x -> x).skip(results.size()));

        // not sure what kind of input is invalid
        return true;
    }

    public double printScores(StudentRecord studentRecord) {
        HashMap<String, Double> scoreRecord = studentRecord.getScoreRecord();

        System.out.println(studentRecord.getStudentName());

        double total = 0;

        for (String s : subjects) {
            double score = scoreRecord.get(s);
            printItem(score);
            total += score;
        }

        printItem(total / subjects.length);
        printItem(total);

        System.out.println();

        return total;
    }

    public void printItem(double score) {
        System.out.print("|" + score);
    }

}
