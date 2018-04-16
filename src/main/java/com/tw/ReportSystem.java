package com.tw;

import java.util.*;

public class ReportSystem {

    private HashMap<String, StudentRecord> records;
    private final String[] SUBJECTS = {"数学", "语文", "英语", "编程"};

    public ReportSystem() {
        records = new HashMap<>();
    }

    public StudentRecord getSingleRecord(String studentID) {
        return records.get(studentID);
    }

    public HashMap<String, StudentRecord> getRecords() {
        return records;
    }

    /*
       Assume that the system only accept input that strictly follows the format:
       "姓名, 学号, 数学: 成绩, 语文: 成绩, 英语: 成绩, 编程: 成绩"
    */
    public boolean addRecord(String input) {

        String[] entries = input.split(", ");

        if (entries.length != 2 + SUBJECTS.length) {
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

    /*
        Assume that the system only accept input that strictly follows the format:
        "学号, 学号, ..."
        And the studentID only contains digit.
     */
    public boolean printRecords(String input) {

        String[] studentIDs = input.split(", ");

        if (!validateIDs(studentIDs)) {
            return false;
        }

        System.out.println("成绩单\n姓名|数学|语文|英语|编程|平均分|总分\n========================");

        List<Double> results = new ArrayList<>();

        double classTotal = 0;

        for(String id : studentIDs) {
            StudentRecord cur = records.get(id);

            if (cur == null) continue;  // 如果我们输入的学号不存在，该学号在计算时就会被忽略。

            double studentTotal = cur.listScores(SUBJECTS);
            classTotal += studentTotal;
            results.add(studentTotal);
        }

        if (results.size() == 0) {
            return true;    // 没有符合的记录，不打印即返回
        }

        System.out.println("========================");

        System.out.printf("全班总分平均数：%.2f\n全班总分中位数：%.2f\n\n", classTotal / results.size(), getMedian(results));

        return true;
    }

    public boolean validateIDs(String[] studentIDs) {

        for (String id: studentIDs) {
            if (!id.matches("\\d+"))
                return false;
        }
        return true;
    }

    public double getMedian(List<Double> numbers) {
        Collections.sort(numbers);

        int size = numbers.size();

        double median;

        if (size % 2 == 0) {
            median = (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2;
        } else {
            median = numbers.get(size / 2);
        }

        return median;
    }

}
