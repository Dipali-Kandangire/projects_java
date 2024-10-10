package com.tka.project;

public class Student {
    private int studId;
    private double marks;

    public Student(int studId, double marks) throws NumberFormatException, IllegalArgumentException {
        if (studId == 0) {
            throw new NumberFormatException("ID cannot be 0");
        }
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks out of range");
        }
        this.studId = studId;
        this.marks = marks;
    }

    public int getStudId() {
        return studId;
    }

    public void setStudId(int studId) {
        this.studId = studId;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student [ID=" + studId + ", Marks=" + marks + "]";
    }
}

package com.tka.project;

import java.util.ArrayList;

public class StudentData {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static void calculateAverageMarks(int id) {
        double totalMarks = 0;
        int count = 0;

        for (Student student : students) {
            if (student.getStudId() == id) {
                totalMarks += student.getMarks();
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Student ID not found.");
        } else {
            double average = totalMarks / count;
            System.out.println("Average marks for student ID " + id + ": " + Math.round(average));
        }
    }

    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
        } else {
            System.out.println("Displaying Student Details:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}
package com.tka.project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char ch = 'Y';

        do {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Student Details");
            System.out.println("3. Calculate Average Marks by Student ID");
            System.out.print("Choice: ");
            
            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                sc.nextLine(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter the total number of students to add:");
                    int n = sc.nextInt();

                    for (int i = 0; i < n; i++) {
                        try {
                            System.out.println("Enter student ID and marks:");
                            Student student = new Student(sc.nextInt(), sc.nextDouble());
                            StudentData.addStudent(student);
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Marks should be a number.");
                            sc.nextLine(); // Clear invalid input
                        } catch (NumberFormatException | IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                            sc.nextLine(); // Clear invalid input
                        }
                    }
                    System.out.println("Details updated!");
                    break;

                case 2:
                    StudentData.displayAllStudents();
                    break;

                case 3:
                    System.out.println("Enter the student ID to calculate average marks:");
                    int id = sc.nextInt();
                    StudentData.calculateAverageMarks(id);
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }

            System.out.println("\nDo you want to continue? (Y/N)");
            ch = sc.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');

        sc.close();
        System.out.println("Program exited.");
    }
}

