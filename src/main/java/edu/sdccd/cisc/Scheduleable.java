
package edu.sdccd.cisc;

import java.time.LocalDateTime;

/**
 * Scheduleable Interface
 * Demonstrates: Topic 8 - Interfaces and Polymorphism
 * Defines contract for all scheduleable items (Task, Event, Homework)
 */
public interface Scheduleable {

    // Topic 8: Interface method declarations (abstract by default)
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    LocalDateTime getDueDate();
    boolean isCompleted();
    void setCompleted(boolean completed);

    // Display information about this scheduleable item
    void displayInfo();

    // Convert to string for file storage
    String serialize();
}