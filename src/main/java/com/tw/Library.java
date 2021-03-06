package com.tw;

import java.util.Scanner;

public class Library {

    public static ReportSystem reportSystem = new ReportSystem();

    public static void main(String[] args) {

        String MENU = "1. 添加学生\n2. 生成成绩单\n3. 退出\n请输入你的选择（1～3）：";

        Scanner sc = new Scanner(System.in);

        int option;

        while (true) {
            System.out.println(MENU);

            // check if input is an integer
            while (!sc.hasNextInt()) {
                System.out.println(MENU);
                sc.next();
            }

            option = sc.nextInt();
            sc.nextLine();  // to consume the "end of line" character

            if (option == 1) {
                System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");

                String input1 = sc.nextLine();

                while (!reportSystem.addRecord(input1.trim())) {
                    System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
                    input1 = sc.nextLine();
                }

            } else if (option == 2) {
                System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");

                String input2 = sc.nextLine();

                while (!reportSystem.printRecords(input2.trim())) {
                    System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
                    input2 = sc.nextLine();
                }

            } else if (option == 3) {
                break;
            }
        }

        sc.close();
    }

}
