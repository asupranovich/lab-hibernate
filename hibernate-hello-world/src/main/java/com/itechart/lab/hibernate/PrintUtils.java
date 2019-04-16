package com.itechart.lab.hibernate;

import java.util.List;

public class PrintUtils {

    public static void printStudents(List<Student> students) {

        System.out.println("|---------- Students ---------|");
        System.out.println("| ID | FIRST NAME | LAST NAME |");

        if (students == null || students.size() == 0) {
            System.out.println("| No students found...        |");
        } else {
            String lineFormat = "|  %s | %2$-10s | %3$-9s |";
            for (Student student : students) {
                Integer id = student.getId();
                String firstName = student.getFirstName();
                String lastName = student.getLastName();
                System.out.println(String.format(lineFormat, id, firstName, lastName));
            }
        }

        System.out.println("|-----------------------------|");
    }

}
