package edu.sdccd.cisc;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
public class FileHandler {
    public static void writeObjectToFile(Event Event, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            System.out.println("Object successfully saved to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

