# Topic Integration in Task Helper

## Topic 1: Java overview, JVM, OOP concepts
- **Where**: Throughout the entire project, especially in Task.java, Event.java, and Homework.java
- **Demonstrated**:
  - Object-Oriented Programming: The project is built around objects representing real-world entities like tasks, events, and homework
  - Encapsulation: Private fields with public getters/setters
  - Abstraction: Interface-based design with Scheduleable
- **Example**: `Task` class with private fields (`title`, `description`, `priority`) and public methods (`getTitle()`, `setTitle()`)

## Topic 2: Variables, types, input/output
- **Where**: Task.java, all form dialogs in UserInterface.java
- **Demonstrated**:
  - Primitive types: `byte` for priority, `int` for estimated minutes, `boolean` for completion
  - Reference types: `String`, `LocalDateTime`, `ArrayList`
  - Input: TextField and TextArea components in GUI forms
  - Output: System.out.println() statements and GUI table display
- **Example**: `private byte priority;` in Task.java, `TextField title = new TextField();` in UserInterface.java

## Topic 3: Control flow: if, switch, loops
- **Where**: Task.java (validatePriority, isOverdue), TaskManager.java (loadFromFile, parseLine), UserInterface.java (showTodayItems)
- **Demonstrated**:
  - If-else statements: Priority validation, null checks
  - Switch statement: Parsing different task types from file
  - For-each loops: Iterating through tasks, events, and homework
  - While loop: Reading lines from save file
- **Example**: `validatePriority()` method uses if-else; `parseLine()` uses switch statement for "TASK", "EVENT", "HOMEWORK"

## Topic 4: Exceptions (intro), debugging
- **Where**: Task.java constructor, Task.deserialize(), Event.deserialize(), Homework.deserialize()
- **Demonstrated**:
  - Throwing exceptions: `throw new IllegalArgumentException()` for empty titles
  - Basic exception handling: Try-catch blocks in deserialization methods
- **Example**: `if (title == null || title.trim().isEmpty()) { throw new IllegalArgumentException("Title cannot be empty"); }`

## Topic 5: Methods, parameters, blocks, scope
- **Where**: All class files, especially Task.java with helper methods
- **Demonstrated**:
  - Method declarations with parameters: `validatePriority(byte priority)`
  - Return types: Methods returning various types (String, boolean, LocalDateTime)
  - Method overloading: Multiple Task constructors
  - Scope: Local variables within methods, instance variables at class level
- **Example**: `private byte validatePriority(byte priority)` - private helper method with parameter and return value

## Topic 6: Arrays & ArrayLists
- **Where**: TaskManager.java, UserInterface.java
- **Demonstrated**:
  - ArrayList Initialization: `private ArrayList<Task> tasks;`
  - ArrayList methods: `add()`, `remove()`, `get()`
  - Enhanced for loops: Iterating through ArrayLists
- **Example**: `ArrayList<Scheduleable> scheduleables = new ArrayList<>();` in UserInterface.java

## Topic 7: Objects & classes
- **Where**: All Java files (Task.java, Event.java, Homework.java, Subject.java, etc.)
- **Demonstrated**:
  - Class declarations with fields and methods
  - Object instantiation: Creating Task, Event, and Homework objects
  - Constructors: Multiple constructors with different parameters
  - Encapsulation: Private fields with public accessor methods
- **Example**: `Task task = new Task(title.getText(), desc.getText(), priority, startDate, endDate, dueDate);`

## Topic 8: Abstract classes & interfaces
- **Where**: Scheduleable.java (interface), Task.java (base class), Event.java and Homework.java (subclasses)
- **Demonstrated**:
  - Interface implementation: Task implements Scheduleable interface
  - Interface methods: `displayInfo()`, `isOverdue()`, `getTitle()` defined in Scheduleable
  - Class inheritance: Event and Homework extend Task
  - Method overriding: `displayInfo()`, `serialize()`, `toString()` overridden in subclasses
  - Polymorphism: Scheduleable references can hold Task, Event, or Homework objects
- **Example**: `public class Task implements Scheduleable` and `public class Event extends Task`

## Topic 9: Files
- **Where**: TaskManager.java (saveToFile, loadFromFile methods)
- **Demonstrated**:
  - File writing: printWriter with FileWriter
  - File reading: scanner reading from File
  - File paths: Using relative paths for data persistence
  - Serialization: Custom serialize/deserialize methods for Task, Event, and Homework
- **Example**: `PrintWriter writer = new PrintWriter(new FileWriter("tasks.txt"));` and `Scanner scanner = new Scanner(new File("tasks.txt"));`

## Topic 10: JavaFX
- **Where**: UserInterface.java, ClockDisplay.java
- **Demonstrated**:
  - JavaFX Application lifecycle: `start()` method
  - UI components: TableView, TextField, TextArea, MenuButton, Dialog
  - Layouts: VBox, ToolBar
  - Event handling: Button actions with lambda expressions
  - Property binding: StringProperty, BooleanProperty, IntegerProperty for table cells
  - Scene and Stage management
- **Example**: `tableView = new TableView<>();` and `addTask.setOnAction(e -> showTaskForm());`

## Topic 11: Robustness & coding standards
- **Where**: All files
- **Demonstrated**:
  - Try-catch blocks: Catching and handling exceptions during file I/O and user input
  - Exception propagation: Methods declaring `throws IOException`
  - Error messages: Printing error details with `System.err.println()`
  - Defensive programming: Null checks and validation
  - Meaningful variable names: `estimatedMinutes`, `dueDate`, `isCompleted`
  - Consistent formatting and indentation
  - JavaDoc-style comments: Method and class documentation
  - Constants: `public static final DateTimeFormatter FILE_FMT`
  - Proper access modifiers: private fields, public methods
  - Separation of concerns: Distinct classes for different responsibilities
- **Example**: `try { parseLine(line); } catch (Exception e) { System.err.println("Error reading line: " + e.getMessage()); }`

## Topic 12: Multithreading
- **Where**: ClockDisplay.java
- **Demonstrated**:
  - Thread creation: `Thread clockThread = new Thread(this, "ClockThread");`
  - Runnable interface: ClockDisplay implements Runnable
  - Thread lifecycle: `start()`, `stop()` methods
  - Thread Synchronization: `volatile boolean running` flag
  - Platform.runLater(): Updating JavaFX UI from background thread safely
  - Continuous execution: `run()` method that updates clock every second
- **Example**: `public class ClockDisplay implements Runnable` with `clockThread.start();` and `Platform.runLater(() -> updateDisplay());`
