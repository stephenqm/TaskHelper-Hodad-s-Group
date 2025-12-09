package edu.sdccd.cisc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event - Represents scheduled events (meetings, appointments, etc.)
 * Demonstrates: Topic 8 - Inheritance and Polymorphism
 * Extends Task to add event-specific fields
 */
public class Event extends Task {

    // Topic 7: Additional fields specific to events
    private String location;
    private String organizer;

    // Topic 8: Constructor that calls parent constructor
    public Event(String title, String description, byte priority,
                 LocalDateTime startDate, LocalDateTime endDate,
                 String location, String organizer) {
        // Call parent Task constructor
        super(title, description, priority, startDate, endDate, endDate);
        this.location = location != null ? location : "";
        this.organizer = organizer != null ? organizer : "";
    }

    // Simplified constructor
    public Event(String title, String description,
                 LocalDateTime startDate, LocalDateTime endDate,
                 String location) {
        this(title, description, (byte) 2, startDate, endDate, location, "");
    }

    // Topic 7: Getters and setters for encapsulation
    public String getLocation() { return location; }
    public String getOrganizer() { return organizer; }
    public void setLocation(String location) {
        this.location = location != null ? location : "";
    }
    public void setOrganizer(String organizer) {
        this.organizer = organizer != null ? organizer : "";
    }

    // Topic 8: Polymorphism - Override parent method
    @Override
    public void displayInfo() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("=== EVENT ===");
        System.out.println("Title: " + getTitle());
        System.out.println("Description: " + getDescription());
        System.out.println("Location: " + location);
        System.out.println("Organizer: " + organizer);
        System.out.println("Start: " + (getStartDate() != null ? getStartDate().format(fmt) : "N/A"));
        System.out.println("End: " + (getEndDate() != null ? getEndDate().format(fmt) : "N/A"));
        System.out.println("Status: " + (isCompleted() ? "COMPLETED" : "SCHEDULED"));
        System.out.println("=============");
    }

    // Topic 8: Override serialize method for file storage
    @Override
    public String serialize() {
        String sStart = getStartDate() == null ? "" : getStartDate().format(FILE_FMT);
        String sEnd = getEndDate() == null ? "" : getEndDate().format(FILE_FMT);
        String sDue = getDueDate() == null ? "" : getDueDate().format(FILE_FMT);

        return "EVENT|" + escape(getTitle()) + "|" + escape(getDescription()) + "|"
                + getPriority() + "|" + sStart + "|" + sEnd + "|" + sDue + "|"
                + escape(location) + "|" + escape(organizer) + "|" + isCompleted();
    }

    // Topic 10: Static factory method with exception handling
    public static Event deserialize(String[] parts) {
        try {
            String title = unescape(parts[1]);
            String desc = unescape(parts[2]);
            byte priority = Byte.parseByte(parts[3]);
            LocalDateTime start = parseDateTime(parts[4]);
            LocalDateTime end = parseDateTime(parts[5]);
            String location = unescape(parts[7]);
            String organizer = unescape(parts[8]);
            boolean completed = Boolean.parseBoolean(parts[9]);

            Event event = new Event(title, desc, priority, start, end, location, organizer);
            event.setCompleted(completed);
            return event;
        } catch (Exception e) {
            System.err.println("Error deserializing event: " + e.getMessage());
            return null;
        }
    }

    // Topic 12: Override toString for better display
    @Override
    public String toString() {
        return "[EVENT] " + getTitle() + " @ " + location;
    }
}