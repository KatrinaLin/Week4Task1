package com.tw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportSystem {

    private HashMap<Integer, Record> records;
    private final int NUMBER_OF_SUBJECT = 4;

    public ReportSystem() {
        records = new HashMap<Integer, Record>();
    }

    public Record getRecord(int studentID) {
        return records.get(studentID);
    }

    /*
        Assume that the system only accept input that strictly follows the format:
        "姓名, 学号, 数学: 成绩, 语文: 成绩, 英语: 成绩, 编程: 成绩"
     */
    public boolean addRecord(String input) {

        String[] entries = input.split(", ");

        if (entries.length != 2 + NUMBER_OF_SUBJECT) {
            return false;
        }

        String name = entries[0].trim();
        int id = Integer.parseInt(entries[1].trim());

        // check if a student record with the same id already exists
        Record cur = records.get(id);

        if (cur == null) {
            cur = new Record(name, id); // instantiate a Record object if the student record does not exist
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


    public List<Record> getRecords(List<Integer> studentIDs) {

        List<Record> result = new ArrayList<>();

        studentIDs.stream()
                .forEach(id -> result.add(records.get(id)));

        return result;
    }


}
