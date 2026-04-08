package entities;

import java.math.BigDecimal;

public class Student {

    // Basic identity
    private String firstName;
    private String lastName;
    private BigDecimal studentId;

    // Grades
    private double[] grades;       // individual assessment grades
    private String[] letterGrades; // letter grades
    private BigDecimal totalCredits;      // total credit hours completed

    // GPA
    private double gpa;            // calculated from grades
    private double[] courseGrades; // grade points per course (e.g. 4.0, 3.0)
    private String[] courseNames;  // name of course according to grade

    private boolean isEnrolled;

    public void
}