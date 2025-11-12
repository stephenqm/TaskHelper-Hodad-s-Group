package edu.sdccd.cisc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Task with title, description, priority, start and end date and due date
public class Task implements Scheduleable {

    private String title;
    private String description;
    private byte priority; // 1 = low, 3 = high
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime dueDate;

    // Constructor
    public Task(String title, String description, byte priority, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
    }

    // getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public byte getPriority() { return priority; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public LocalDateTime getDueDate() { return dueDate; }

    // Logic to check if it is overdue/ late
    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(dueDate);
    }

    // Info
    public void displayTaskInfo() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("🗓️ Task: " + title);
        System.out.println("📄 Description: " + description);
        System.out.println("⭐ Priority: " + priority);
        System.out.println("📆 Start: " + startDate.format(fmt));
        System.out.println("⏰ Due: " + dueDate.format(fmt));
        System.out.println(isOverdue() ? "⚠️ Status: OVERDUE" : "✅ Status: On time");
        System.out.println();
    }
}