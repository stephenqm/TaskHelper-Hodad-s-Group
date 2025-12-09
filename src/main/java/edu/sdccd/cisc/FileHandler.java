package edu.sdccd.cisc;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class FileHandler {
    public static void writeObjectToFile(Event event, String fileName) {  // Changed Event to event
        try (FileOutputStream fos = new FileOutputStream(fileName);  // Changed filename to fileName
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(event);  // Changed obj to event
            System.out.println("Object successfully saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}