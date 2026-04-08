package entities;

import java.math.BigDecimal;

public class Student {

    // variable types (specifically those with []) assisted by claude.ai
    private String firstName;
    private String lastName;
    private String[] courses; // keeps list of courses being taken
    private int courseCount; // keeps track of number of courses being added

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = new String[5]; // sets max number of courses students can take
        this.courseCount = 0; // initializes course count
    }

    public void addCourse(String courseName, double score) {
        if (courseCount >= 5) {
            throw new RuntimeException("Cannot add more than 5 courses");
        }
        courses[courseCount] = courseName; // stores course name in array
        courseCount++;
    }

    // assisted by Claude.ai
    public void classList() {
        System.out.println("=== Student Class List ===");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Courses:");
        for (int i = 0; i < courseCount; i++) {
            System.out.println(" " + courses[i]);
        }
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String[] getCourses() { return courses; }
}