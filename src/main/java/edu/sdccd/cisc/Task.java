package edu.sdccd.cisc;

import java.time.LocalDateTime;

//base class for everything
public class Task implements Scheduleable {

    //variables shared by all tasks
    protected String title;
    protected String description;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected boolean completed;

    //initializes a task
    public Task(String title, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    // getters

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    // setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

   //mark task completed
    public void markCompleted() {
        this.completed = true;
    }

    //displays task in console
    @Override
    public void displayDetails() {
        System.out.println("📝 Task Details:");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Start: " + startDate);
        System.out.println("End: " + endDate);
        System.out.println("Status: " + (completed ? "Completed ✅" : "In Progress ⏳"));
        System.out.println("------------------------------");
    }

    //how many hours between start and end time
    public long getDurationHours() {
        return java.time.Duration.between(startDate, endDate).toHours();
    }
}