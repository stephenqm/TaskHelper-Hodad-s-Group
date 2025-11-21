package edu.sdccd.cisc;

public class Subject {

    private String name;

    // constructor
    public Subject(String name) {
        this.name = name;
    }

    // getter
    public String getName() {
        return name;
    }
    
    // override
    @Override
    public String toString() {
        return name;
    }
}
