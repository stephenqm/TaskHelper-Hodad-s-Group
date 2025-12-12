package edu.sdccd.cisc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TaskManager - Simple data management
 * Topics: 6 (ArrayLists), 10 (exceptions), 11 (files)
 */
public class TaskManager {

    // Topic 6: ArrayLists
    private ArrayList<Task> tasks;
    private ArrayList<Event> events;
    private ArrayList<Homework> homeworkList;

    private static final String SAVE_FILE = "data.txt";

    // Constructor
    public TaskManager() {
        tasks = new ArrayList<>();
        events = new ArrayList<>();
        homeworkList = new ArrayList<>();
    }

    // === ADD METHODS ===

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addHomework(Homework homework) {
        homeworkList.add(homework);
    }

    // === REMOVE METHODS ===

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void removeHomework(Homework homework) {
        homeworkList.remove(homework);
    }

    // GET METHOD

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public ArrayList<Event> getAllEvents() {
        return events;
    }

    public ArrayList<Homework> getAllHomework() {
        return homeworkList;
    }

    // === FILE I/O (Topic 11) ===

    // Save to file
    public void saveToFile() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE));

        // Topic 3: Loop through tasks
        for (Task t : tasks) {
            writer.println(t.serialize());
        }

        for (Event e : events) {
            writer.println(e.serialize());
        }

        for (Homework h : homeworkList) {
            writer.println(h.serialize());
        }

        writer.close();
        System.out.println("Data saved!");
    }

    // Load from file
    public void loadFromFile() throws IOException {
        File file = new File(SAVE_FILE);

        // Topic 3: If statement
        if (!file.exists()) {
            System.out.println("No save file found.");
            return;
        }

        Scanner scanner = new Scanner(file);

        // Topic 3: While loop
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                continue;
            }

            // Topic 10: Try-catch for errors
            try {
                parseLine(line);
            } catch (Exception e) {
                System.err.println("Error reading line: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Data loaded!");
    }

    // Parse one line from file
    private void parseLine(String line) {
        String[] parts = line.split("\\|");

        String type = parts[0];

        // Topic 3: Switch statement
        switch (type) {
            case "TASK":
                Task task = Task.deserialize(parts);
                if (task != null) {
                    tasks.add(task);
                }
                break;

            case "EVENT":
                Event event = Event.deserialize(parts);
                if (event != null) {
                    events.add(event);
                }
                break;

            case "HOMEWORK":
                Homework homework = Homework.deserialize(parts);
                if (homework != null) {
                    homeworkList.add(homework);
                }
                break;
        }
    }
}