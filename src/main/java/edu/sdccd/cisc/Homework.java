package edu.sdccd.cisc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Homework extends Task {

    private Subject subject;
    private int estimatedMinutes;

    public Homework(String title, String description, byte priority, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime dueDate, Subject subject, int estimatedMinutes) {

        super(title, description, priority, startDate, endDate, dueDate);
        this.subject = subject;
        this.estimatedMinutes = estimatedMinutes;

    }

    // getter
    public Subject getSubject() { return subject; }
    public int getEstimatedMinutes() { return estimatedMinutes; }

    @Override
    public void displayTaskInfo() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("Assignment: " + getTitle());
        System.out.println("Subject: " + subject); // uses subject.toString()
        System.out.println("Description: " + getDescription());
        System.out.println("Priority: " + getPriority());
        System.out.println("Estimated time to complete: " + estimatedMinutes + " minutes");
        System.out.println("Start date: " + getStartDate().format(fmt));
        System.out.println("Due date: " + getDueDate().format(fmt));
        System.out.println();
    }
}
