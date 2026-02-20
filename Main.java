/*
 * Name: Trinity Hurtado, Rito Murllio
 * Date: 02/20/2026
 * Program: Course Grades Analyzer - reads CSV grade totals and analyzes A percentages.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        File csvFile = new File("courseAndGradesData.csv");

        // Check if file exists
        if (!csvFile.exists()) {
            System.out.println("Error: grades.csv not found in the current directory.");
            System.out.println("Please make sure grades.csv is in the same folder as Main.java.");
            return;
        }

        try (Scanner file = new Scanner(csvFile)) {
            while (file.hasNextLine()) {
                String line = file.nextLine().trim();

                // Skip blank lines or the header
                if (line.isEmpty() || line.startsWith("Course")) {
                    continue;
                }

                String[] parts = line.split(",");
                String courseName = parts[0].trim();
                ArrayList<Integer> grades = new ArrayList<>();

                try {
                    for (int i = 1; i < parts.length; i++) {
                        grades.add(Integer.parseInt(parts[i].trim())); // parse grade counts
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid line: " + line);
                    continue; // skip this line if numbers are invalid
                }

                // Check for duplicates and combine grades if needed
                boolean found = false;
                for (Course c : courses) {
                    if (c.getCourseName().equalsIgnoreCase(courseName)) {
                        c.addGrades(grades);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    courses.add(new Course(courseName, grades));
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Print summary table
        System.out.printf("%-10s %5s %5s %5s %5s %5s %7s %7s%n",
                "Course", "A", "B", "C", "D", "F", "Total", "A%");
        for (Course c : courses) {
            System.out.println(c);
        }

        // Find course with highest A%
        if (!courses.isEmpty()) {
            Course best = courses.get(0);
            for (Course c : courses) {
                if (c.getAPercent() > best.getAPercent()) {
                    best = c;
                }
            }
            System.out.println("\nCourse with highest A%:");
            System.out.println(best);
        }

        // User search
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("\nEnter a course name to search: ");
            String searchName = input.nextLine().trim();
            boolean found = false;
            for (Course c : courses) {
                if (c.getCourseName().equalsIgnoreCase(searchName)) {
                    System.out.println("Course found:");
                    System.out.println(c);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Course " + searchName + " not found.");
            }
        }
    }
}



