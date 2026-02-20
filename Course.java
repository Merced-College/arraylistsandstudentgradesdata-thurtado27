/*
 * Name: Trinity Hurtado, Rito Murllio
 * Date: 02/20/2026
 * Program: Course Grades Analyzer - reads CSV grade totals and analyzes A percentages.
 */

import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<Integer> courseGrades; // index 0 = A, 1 = B, 2 = C, 3 = D, 4 = F

    // Constructor
    public Course(String courseName, ArrayList<Integer> courseGrades) {
        this.courseName = courseName;
        this.courseGrades = new ArrayList<>(courseGrades); // copy grades
    }

    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Integer> getCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(ArrayList<Integer> courseGrades) {
        this.courseGrades = new ArrayList<>(courseGrades);
    }

    // Total number of grades
    public int getTotalGrades() {
        int total = 0;
        for (int grade : courseGrades) {
            total += grade;
        }
        return total;
    }

    // A% calculation
    public double getAPercent() {
        int total = getTotalGrades();
        if (total == 0) return 0.0;
        return (double) courseGrades.get(0) / total * 100.0;
    }

    // toString method for printing
    @Override
    public String toString() {
        return String.format("%-10s %5d %5d %5d %5d %5d %7d %7.2f",
                courseName,
                courseGrades.get(0),
                courseGrades.get(1),
                courseGrades.get(2),
                courseGrades.get(3),
                courseGrades.get(4),
                getTotalGrades(),
                getAPercent());
    }

    // Method to add grades (used if a duplicate course exists in CSV)
    public void addGrades(ArrayList<Integer> otherGrades) {
        for (int i = 0; i < courseGrades.size(); i++) {
            courseGrades.set(i, courseGrades.get(i) + otherGrades.get(i));
        }
    }
}
