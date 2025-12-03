package edu.sdccd.cisc;

import java.time.LocalDateTime;

/**
 * Event: represents scheduled events; extends Task.
 */
public class Event extends Task {

    private String location;
    private String organizer;

    public Event(String title, String description, byte priority,
                 LocalDateTime startDate, LocalDateTime endDate,
                 String location, String organizer) {
        // call Task constructor (use dueDate = endDate by default)
        super(title, description, priority, startDate, endDate, endDate);
        this.location = location;
        this.organizer = organizer;
    }

    public String getLocation() { return location; }
    public String getOrganizer() { return organizer; }
    public void setLocation(String location) { this.location = location; }
    public void setOrganizer(String organizer) { this.organizer = organizer; }

    @Override
    public void displayDetails() {
        System.out.println("----- EVENT -----");
        System.out.println("Title: " + getTitle());
        System.out.println("Description: " + getDescription());
        System.out.println("Location: " + location);
        System.out.println("Organizer: " + organizer);
        System.out.println("Start: " + (getStartDate() != null ? getStartDate().format(FILE_FMT) : "N/A"));
        System.out.println("End:   " + (getEndDate()   != null ? getEndDate().format(FILE_FMT)   : "N/A"));
        System.out.println("-----------------");
    }

    // override toDataLine to label as EVENT and include location/organizer in extras
    @Override
    public String toDataLine() {
        String sStart = getStartDate() == null ? "" : getStartDate().format(FILE_FMT);
        String sEnd   = getEndDate()   == null ? "" : getEndDate().format(FILE_FMT);
        String sDue   = getDueDate()   == null ? "" : getDueDate().format(FILE_FMT);
        return String.join(",",
                "EVENT",
                escape(getTitle()),
                escape(getDescription()),
                Byte.toString(getPriority()),
                sStart,
                sEnd,
                sDue,
                escape(location),
                escape(organizer),
                Boolean.toString(isCompleted())
        );
    }

    // parser
    public static Event fromDataParts(String[] parts) {
        // parts[0] == "EVENT"
        String title = unescape(parts[1]);
        String desc  = unescape(parts[2]);
        byte priority = 1;
        try { priority = Byte.parseByte(parts[3]); } catch (Exception ignored) {}
        LocalDateTime start = parseOrNull(parts[4]);
        LocalDateTime end   = parseOrNull(parts[5]);
        // parts[6] dueDate (we set due=end)
        String location = parts.length > 7 ? unescape(parts[7]) : "";
        String organizer= parts.length > 8 ? unescape(parts[8]) : "";
        boolean completed = parts.length > 9 && Boolean.parseBoolean(parts[9]);
        Event e = new Event(title, desc, priority, start, end, location, organizer);
        e.setCompleted(completed);
        return e;
    }

    // reuse escape/unescape defined in Task (they are private there); duplicate simple versions here:
    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\n", "\\n").replace(",", "\\,");
    }
    private static String unescape(String s) {
        if (s == null) return "";
        return s.replace("\\n", "\n").replace("\\,", ",");
    }
}