package edu.sdccd.cisc;

import java.time.LocalDateTime;

public interface Scheduleable {

    // Returns the start date/time of the item
    LocalDateTime getStartDate();

    // Returns the end date/time of the item
    LocalDateTime getEndDate();

    // Returns the due date, if applicable
    LocalDateTime getDueDate();

    // Checks if the item is completed
    boolean isCompleted();

    // Sets the completion status
    void setCompleted(boolean completed);

    // Displays info about this schedulable item
    void displayTaskInfo();

    // Converts the item to a string for saving/loading
    String serialize();
}