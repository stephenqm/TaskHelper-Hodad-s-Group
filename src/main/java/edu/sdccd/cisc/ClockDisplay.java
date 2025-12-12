package edu.sdccd.cisc;


import javafx.application.Platform;
import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Topic 9: Multithreading
public class ClockDisplay extends Label implements Runnable {

    private Thread clockThread;
    private volatile boolean running;
    private final DateTimeFormatter formatter;

    /**
     * Constructor - initializes the clock display
     */
    public ClockDisplay() {
        // Format: "Day, Month DD, YYYY - HH:MM:SS AM/PM"
        formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy - hh:mm:ss a");

        // Style the clock label
        setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5px;");

        // Initialize with current time
        updateTime();

        // Start the clock thread
        start();
    }

    /**
     * Starts the clock thread
     */
    public void start() {
        if (clockThread == null || !clockThread.isAlive()) {
            running = true;
            clockThread = new Thread(this, "ClockThread");
            clockThread.setDaemon(true); // Daemon thread stops when app closes
            clockThread.start();
        }
    }

    /**
     * Stops the clock thread
     */
    public void stop() {
        running = false;
        if (clockThread != null) {
            clockThread.interrupt();
        }
    }

    /**
     * Thread's run method - updates the clock every second
     * Topic 9: Multithreading with Runnable interface
     */
    @Override
    public void run() {
        while (running) {
            try {
                // Update the UI on the JavaFX Application Thread
                Platform.runLater(this::updateTime);

                // Sleep for 1 second (1000 milliseconds)
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                // Thread was interrupted, exit gracefully
                running = false;
            }
        }
    }

    /**
     * Updates the clock text with current time
     */
    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        setText(now.format(formatter));
    }
}