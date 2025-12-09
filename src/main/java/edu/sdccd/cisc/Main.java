package edu.sdccd.cisc;

/**
 * Main - Application entry point
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   Task Helper - Productivity Manager");
        System.out.println("==========================================\n");

        try {
            // Launch JavaFX application
            UserInterface.main(args);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}