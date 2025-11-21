package edu.sdccd.cisc;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Master lists of scheduleable items
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<Homework> homeworkList = new ArrayList<>();

    private static final String SAVE_FILE = "data.txt";

    public static void main(String[] args) {

        loadFromFile();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Hodad's Task Helper ===");
            System.out.println("1. Create Task");
            System.out.println("2. Create Event");
            System.out.println("3. Create Homework");
            System.out.println("4. Show Today's Overview");
            System.out.println("5. Check for Reminders");
            System.out.println("6. Save & Exit");

            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> createTask(input);
                case 2 -> createEvent(input);
                case 3 -> createHomework(input);
                case 4 -> showTodayOverview();
                case 5 -> checkReminders();
                case 6 -> {
                    saveToFile();
                    System.out.println("Saved. Goodbye!");
                    return;
                }
            }
        }
    }

    // ---------------------------
    // CREATE ITEMS
    // ---------------------------

    private static void createTask(Scanner in) {
        System.out.print("Title: ");
        String title = in.nextLine();

        System.out.print("Description: ");
        String desc = in.nextLine();

        System.out.print("Due date (yyyy-mm-dd): ");
        LocalDate due = LocalDate.parse(in.nextLine());

        System.out.print("Priority (1-3): ");
        byte priority = in.nextByte();

        System.out.print("Minutes to complete: ");
        int minutes = in.nextInt();
        in.nextLine();

        Task t = new Task(title, desc, due.atStartOfDay(), priority, minutes);
        tasks.add(t);

        System.out.println("Task created.");
    }

    private static void createEvent(Scanner in) {
        System.out.print("Title: ");
        String title = in.nextLine();

        System.out.print("Description: ");
        String desc = in.nextLine();

        System.out.print("Start (yyyy-mm-ddTHH:MM): ");
        LocalDateTime start = LocalDateTime.parse(in.nextLine());

        System.out.print("End (yyyy-mm-ddTHH:MM): ");
        LocalDateTime end = LocalDateTime.parse(in.nextLine());

        System.out.print("Location: ");
        String location = in.nextLine();

        Event e = new Event(title, desc, start, end, location);
        events.add(e);

        System.out.println("Event created.");
    }

    private static void createHomework(Scanner in) {
        System.out.print("Title: ");
        String title = in.nextLine();

        System.out.print("Description: ");
        String desc = in.nextLine();

        System.out.print("Due date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(in.nextLine());

        System.out.print("Subject name: ");
        String name = in.nextLine();
        Subject s = new Subject(name);

        Homework hw = new Homework(title, desc, date.atStartOfDay(), s);
        homeworkList.add(hw);

        System.out.println("Homework created.");
    }

    // ---------------------------
    // DAILY OVERVIEW
    // ---------------------------

    private static void showTodayOverview() {
        LocalDate today = LocalDate.now();

        System.out.println("\n=== TODAY'S OVERVIEW ===");

        System.out.println("\n-- Tasks Due Today --");
        for (Task t : tasks) {
            if (t.getStartDate().toLocalDate().equals(today)) {
                System.out.println("• " + t.getTitle());
            }
        }

        System.out.println("\n-- Events Today --");
        for (Event e : events) {
            if (e.getStartDate().toLocalDate().equals(today)) {
                System.out.println("• " + e.getTitle() + " at " + e.getStartDate().toLocalTime());
            }
        }

        System.out.println("\n-- Homework Due Today --");
        for (Homework h : homeworkList) {
            if (h.getStartDate().toLocalDate().equals(today)) {
                System.out.println("• " + h.getTitle() + " (" + h.getSubject().getName() + ")");
            }
        }
    }

    // ---------------------------
    // REMINDERS
    // ---------------------------

    private static void checkReminders() {
        LocalDateTime now = LocalDateTime.now();

        System.out.println("\n=== UPCOMING REMINDERS ===");

        for (Task t : tasks) {
            if (!t.isCompleted() &&
                    t.getStartDate().isAfter(now) &&
                    t.getStartDate().isBefore(now.plusHours(1))) {

                System.out.println("Reminder: Task '" + t.getTitle() + "' is due soon!");
            }
        }

        for (Event e : events) {
            if (e.getStartDate().isAfter(now) &&
                    e.getStartDate().isBefore(now.plusHours(1))) {

                System.out.println("Reminder: Event '" + e.getTitle() + "' is starting soon!");
            }
        }
    }

    // FILE I/O

    private static void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(SAVE_FILE))) {

            for (Task t : tasks) {
                out.println("TASK|" + t.serialize());
            }

            for (Event e : events) {
                out.println("EVENT|" + e.getTitle() + "|" + e.getDescription() + "|" +
                        e.getStartDate() + "|" + e.getEndDate() + "|" + e.getLocation());
            }

            for (Homework h : homeworkList) {
                out.println("HOMEWORK|" + h.getTitle() + "|" + h.getDescription() + "|" +
                        h.getStartDate() + "|" + h.getSubject().getName());
            }

        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return;

        try (Scanner scan = new Scanner(file)) {

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split("\\|");

                switch (parts[0]) {
                    case "TASK" -> tasks.add(Task.deserialize(parts));
                    case "EVENT" -> {
                        String title = parts[1];
                        String desc = parts[2];
                        LocalDateTime start = LocalDateTime.parse(parts[3]);
                        LocalDateTime end = LocalDateTime.parse(parts[4]);
                        String loc = parts[5];
                        events.add(new Event(title, desc, start, end, loc));
                    }
                    case "HOMEWORK" -> {
                        String title = parts[1];
                        String desc = parts[2];
                        LocalDateTime date = LocalDateTime.parse(parts[3]);
                        Subject sub = new Subject(parts[4]);
                        homeworkList.add(new Homework(title, desc, date, sub));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}