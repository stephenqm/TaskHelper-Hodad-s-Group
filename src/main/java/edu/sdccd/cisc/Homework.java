package edu.sdccd.cisc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Homework extends Task {

    private Subject subject;

    public Homework(String title, String description, byte priority,
                    LocalDateTime startDate, LocalDateTime endDate,
                    LocalDateTime dueDate, Subject subject, int estimatedMinutes) {
        super(title, description, priority, startDate, endDate, dueDate);
        this.subject = subject;
        setEstimatedMinutes(estimatedMinutes);
    }

    // Simplified constructor
    public Homework(String title, String description,
                    LocalDateTime dueDate, Subject subject) {
        this(title, description, (byte) 2, dueDate, dueDate, dueDate, subject, 0);
    }

    // getter
    public Subject getSubject() { return subject; }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void displayInfo() {  // Changed from displayTaskInfo to displayInfo
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("=== HOMEWORK ===");
        System.out.println("Assignment: " + getTitle());
        System.out.println("Subject: " + subject.getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Priority: " + getPriority());
        System.out.println("Estimated time: " + getEstimatedMinutes() + " minutes");
        System.out.println("Due date: " + getDueDate().format(fmt));
        System.out.println("================");
    }

    @Override
    public String serialize() {
        String sStart = getStartDate() == null ? "" : getStartDate().format(FILE_FMT);
        String sEnd = getEndDate() == null ? "" : getEndDate().format(FILE_FMT);
        String sDue = getDueDate() == null ? "" : getDueDate().format(FILE_FMT);

        return "HOMEWORK|" + escape(getTitle()) + "|" + escape(getDescription()) + "|"
                + getPriority() + "|" + sStart + "|" + sEnd + "|" + sDue + "|"
                + getEstimatedMinutes() + "|" + escape(subject.getName()) + "|" + isCompleted();
    }

    public static Homework deserialize(String[] parts) {
        try {
            String title = unescape(parts[1]);
            String desc = unescape(parts[2]);
            byte priority = Byte.parseByte(parts[3]);
            LocalDateTime start = parseDateTime(parts[4]);
            LocalDateTime end = parseDateTime(parts[5]);
            LocalDateTime due = parseDateTime(parts[6]);
            int minutes = Integer.parseInt(parts[7]);
            String subjectName = unescape(parts[8]);
            boolean completed = Boolean.parseBoolean(parts[9]);

            Subject subject = new Subject(subjectName);
            Homework hw = new Homework(title, desc, priority, start, end, due, subject, minutes);
            hw.setCompleted(completed);
            return hw;
        } catch (Exception e) {
            System.err.println("Error deserializing homework: " + e.getMessage());
            return null;
        }
    }
}