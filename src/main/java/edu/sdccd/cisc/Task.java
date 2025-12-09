package edu.sdccd.cisc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task - Base class for all scheduleable items
 * Demonstrates: Topics 1 (OOP), 2 (variables/types), 5 (methods),
 *               7 (classes), 8 (inheritance), 10 (exceptions), 12 (coding standards)
 */
public class Task implements Scheduleable {

    // Topic 2: Variables and types - instance variables with proper types
    private String title;
    private String description;
    private byte priority; // Topic 2: byte type (1-3, where 1=low, 3=high)
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime dueDate;
    private boolean completed; // Topic 2: boolean type
    private int estimatedMinutes; // Topic 2: int type

    // Topic 12: Coding standards - constant for date formatting
    public static final DateTimeFormatter FILE_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // Topic 5: Constructor with parameters and proper scope
    public Task(String title, String description, byte priority,
                LocalDateTime startDate, LocalDateTime endDate,
                LocalDateTime dueDate) {
        // Topic 10: Input validation with exception handling
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        this.title = title;
        this.description = description != null ? description : "";
        this.priority = validatePriority(priority); // Topic 5: method call
        this.startDate = startDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.completed = false;
        this.estimatedMinutes = 0;
    }

    // Topic 5: Overloaded constructor (method overloading)
    public Task(String title, String description, LocalDateTime dueDate,
                byte priority, int estimatedMinutes) {
        this(title, description, priority, dueDate, dueDate, dueDate);
        this.estimatedMinutes = estimatedMinutes;
    }

    // Topic 5: Private helper method with return value and parameter
    // Topic 3: Control flow with if-else
    private byte validatePriority(byte priority) {
        if (priority < 1) {
            return 1; // minimum priority
        } else if (priority > 3) {
            return 3; // maximum priority
        } else {
            return priority;
        }
    }

    // Topic 7: Getters (encapsulation - public access to private data)
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public byte getPriority() { return priority; }
    public int getEstimatedMinutes() { return estimatedMinutes; }

    // Topic 7: Setters with validation
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
    }

    public void setPriority(byte priority) {
        this.priority = validatePriority(priority);
    }

    public void setEstimatedMinutes(int minutes) {
        // Topic 3: If statement for validation
        if (minutes >= 0) {
            this.estimatedMinutes = minutes;
        }
    }

    // Topic 8: Interface implementation (from Scheduleable)
    @Override
    public LocalDateTime getStartDate() { return startDate; }

    @Override
    public LocalDateTime getEndDate() { return endDate; }

    @Override
    public LocalDateTime getDueDate() { return dueDate; }

    @Override
    public boolean isCompleted() { return completed; }

    @Override
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Topic 5: Method with logic
    // Topic 3: If statement for comparison
    public boolean isOverdue() {
        if (completed) {
            return false; // not overdue if already completed
        }
        return LocalDateTime.now().isAfter(dueDate);
    }

    // Topic 8: Polymorphism - can be overridden by subclasses
    // Topic 2: Output demonstration
    @Override
    public void displayInfo() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("=== TASK ===");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Priority: " + priority + "/3");
        System.out.println("Start: " + (startDate != null ? startDate.format(fmt) : "N/A"));
        System.out.println("Due: " + (dueDate != null ? dueDate.format(fmt) : "N/A"));
        System.out.println("Status: " + (completed ? "COMPLETED" : (isOverdue() ? "OVERDUE" : "PENDING")));
        System.out.println("Estimated time: " + estimatedMinutes + " minutes");
        System.out.println("============");
    }

    // Topic 11: Serialization for file storage
    @Override
    public String serialize() {
        String sStart = startDate == null ? "" : startDate.format(FILE_FMT);
        String sEnd = endDate == null ? "" : endDate.format(FILE_FMT);
        String sDue = dueDate == null ? "" : dueDate.format(FILE_FMT);

        return "TASK|" + escape(title) + "|" + escape(description) + "|"
                + priority + "|" + sStart + "|" + sEnd + "|" + sDue + "|"
                + estimatedMinutes + "|" + completed;
    }

    // Topic 11: Deserialization from file
    // Topic 10: Exception handling with try-catch
    public static Task deserialize(String[] parts) {
        try {
            String title = unescape(parts[1]);
            String desc = unescape(parts[2]);
            byte priority = Byte.parseByte(parts[3]);
            LocalDateTime start = parseDateTime(parts[4]);
            LocalDateTime end = parseDateTime(parts[5]);
            LocalDateTime due = parseDateTime(parts[6]);
            int minutes = Integer.parseInt(parts[7]);
            boolean completed = Boolean.parseBoolean(parts[8]);

            Task task = new Task(title, desc, priority, start, end, due);
            task.setEstimatedMinutes(minutes);
            task.setCompleted(completed);
            return task;
        } catch (Exception e) {
            // Topic 10: Exception handling
            System.err.println("Error deserializing task: " + e.getMessage());
            return null;
        }
    }

    // Topic 5: Protected helper methods for subclasses
    public static LocalDateTime parseDateTime(String s) {
        // Topic 3: If-else control flow
        if (s == null || s.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(s, FILE_FMT);
        } catch (Exception e) {
            return null;
        }
    }

    // Topic 5: String manipulation methods
    public static String escape(String s) {
        if (s == null) return "";
        return s.replace("|", "\\|").replace("\n", "\\n");
    }

    public static String unescape(String s) {
        if (s == null) return "";
        return s.replace("\\|", "|").replace("\\n", "\n");
    }

    // Topic 12: Override toString for better object representation
    @Override
    public String toString() {
        return title + (completed ? " ✓" : (isOverdue() ? " ⚠" : ""));
    }
}