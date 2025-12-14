# Topic Integration in Task Helper

## Topic 1: Java overview, JVM, OOP concepts
- **Where**: Throughout the entire project, especially in Task.java, Event.java, and Homework.java
- **Demonstrated**:
  - Object-Oriented Programming: The project is built around objects representing real-world entities like tasks, events, and homework
  - Encapsulation: Private fields with public getters/setters
  - Abstraction: Interface-based design with Scheduleable  
<img width="666" height="94" alt="Screenshot 2025-12-13 at 10 29 17 PM" src="https://github.com/user-attachments/assets/c8d11598-59a4-41d6-adfe-15f4bb72a884" />
<img width="527" height="541" alt="Screenshot 2025-12-13 at 10 30 38 PM" src="https://github.com/user-attachments/assets/caf87c5c-65df-48cb-a2dd-2607df7e405e" />
(Homework has the same attributes as tasks, along with a subject. Using the getters from task.java, outside files are able to read those parts of task.java)

## Topic 2: Variables, types, input/output
- **Where**: Task.java, all form dialogs in UserInterface.java
- **Demonstrated**:
  - Primitive types: `byte` for priority, `int` for estimated minutes, `boolean` for completion
  - Reference types: `String`, `LocalDateTime`, `ArrayList`
  - Input: TextField and TextArea components in GUI forms
  - Output: System.out.println() statements and GUI table display
<img width="709" height="190" alt="Screenshot 2025-12-13 at 10 31 58 PM" src="https://github.com/user-attachments/assets/4cf5bcee-e24d-4dd8-a711-b2f17b7b50ae" />
<img width="527" height="487" alt="Screenshot 2025-12-13 at 10 32 22 PM" src="https://github.com/user-attachments/assets/045d797a-08b6-4734-9e1a-6babcaa1441f" />
(Users input a string for title and description, but a byte for priority)

## Topic 3: Control flow: if, switch, loops
- **Where**: Task.java (validatePriority, isOverdue), TaskManager.java (loadFromFile, parseLine), UserInterface.java (showTodayItems)
- **Demonstrated**:
  - If-else statements: Priority validation, null checks
  - Switch statement: Parsing different task types from file
  - For-each loops: Iterating through tasks, events, and homework
  - While loop: Reading lines from save file
<img width="593" height="78" alt="Screenshot 2025-12-13 at 10 33 46 PM" src="https://github.com/user-attachments/assets/b1dacd7a-570b-4ca5-9703-d36d92c41ba6" />
<img width="527" height="487" alt="Screenshot 2025-12-13 at 10 32 22 PM" src="https://github.com/user-attachments/assets/18c1a957-32d8-4a0f-9e11-3aba3aedbb8d" />
<img width="1049" height="205" alt="Screenshot 2025-12-13 at 10 34 13 PM" src="https://github.com/user-attachments/assets/fc1cd332-8a31-4a2d-8864-e625daa34bee" />
(When user inputted nothing for title, error message printed in terminal and prevented the creation of a task)

## Topic 4: Exceptions (intro), debugging
- **Where**: Task.java constructor, Task.deserialize(), Event.deserialize(), Homework.deserialize()
- **Demonstrated**:
  - Throwing exceptions: `throw new IllegalArgumentException()` for empty titles
  - Basic exception handling: Try-catch blocks in deserialization methods
<img width="593" height="78" alt="Screenshot 2025-12-13 at 10 33 46 PM" src="https://github.com/user-attachments/assets/b1dacd7a-570b-4ca5-9703-d36d92c41ba6" />
<img width="527" height="487" alt="Screenshot 2025-12-13 at 10 32 22 PM" src="https://github.com/user-attachments/assets/18c1a957-32d8-4a0f-9e11-3aba3aedbb8d" />
<img width="1049" height="205" alt="Screenshot 2025-12-13 at 10 34 13 PM" src="https://github.com/user-attachments/assets/fc1cd332-8a31-4a2d-8864-e625daa34bee" />
(When user inputted nothing for title, error message printed in terminal and prevented the creation of a task)

## Topic 5: Methods, parameters, blocks, scope
- **Where**: All class files, especially Task.java with helper methods
- **Demonstrated**:
  - Method declarations with parameters: `validatePriority(byte priority)`
  - Return types: Methods returning various types (String, boolean, LocalDateTime)
  - Method overloading: Multiple Task constructors
  - Scope: Local variables within methods, instance variables at class level
<img width="572" height="209" alt="Screenshot 2025-12-13 at 10 37 22 PM" src="https://github.com/user-attachments/assets/5e03201c-2931-4573-ab5b-0896a748f31b" />
<img width="527" height="487" alt="Screenshot 2025-12-13 at 10 38 08 PM" src="https://github.com/user-attachments/assets/72a2fb66-33f7-4ef0-ac20-e52afec55e22" />
<img width="982" height="56" alt="Screenshot 2025-12-13 at 10 38 23 PM" src="https://github.com/user-attachments/assets/5edfe392-eacf-4924-abcd-c8bef889afec" />
(When user inputted a number higher than 3, method returns 3)

## Topic 6: Arrays & ArrayLists
- **Where**: TaskManager.java, UserInterface.java
- **Demonstrated**:
  - ArrayList Initialization: `private ArrayList<Task> tasks;`
  - ArrayList methods: `add()`, `remove()`, `get()`
  - Enhanced for loops: Iterating through ArrayLists
<img width="532" height="85" alt="Screenshot 2025-12-13 at 10 40 24 PM" src="https://github.com/user-attachments/assets/b411cee5-6afd-452d-a00c-d0ebb7d1a576" />
<img width="991" height="516" alt="Screenshot 2025-12-13 at 10 42 17 PM" src="https://github.com/user-attachments/assets/30723708-5371-4c81-93d7-bbc1d5829fe2" />
(ArrayLists implemented, so users can add as many tasks as they want)

## Topic 7: Objects & classes
- **Where**: All Java files (Task.java, Event.java, Homework.java, Subject.java, etc.)
- **Demonstrated**:
  - Class declarations with fields and methods
  - Object instantiation: Creating Task, Event, and Homework objects
  - Constructors: Multiple constructors with different parameters
  - Encapsulation: Private fields with public accessor methods
<img width="666" height="94" alt="Screenshot 2025-12-13 at 10 29 17 PM" src="https://github.com/user-attachments/assets/c8d11598-59a4-41d6-adfe-15f4bb72a884" />
<img width="527" height="541" alt="Screenshot 2025-12-13 at 10 30 38 PM" src="https://github.com/user-attachments/assets/caf87c5c-65df-48cb-a2dd-2607df7e405e" />
(Homework has the same attributes as tasks, along with a subject. Using the getters from task.java, outside files are able to read those parts of task.java)

## Topic 8: Abstract classes & interfaces
- **Where**: Scheduleable.java (interface), Task.java (base class), Event.java and Homework.java (subclasses)
- **Demonstrated**:
  - Interface implementation: Task implements Scheduleable interface
  - Interface methods: `displayInfo()`, `isOverdue()`, `getTitle()` defined in Scheduleable
  - Class inheritance: Event and Homework extend Task
  - Method overriding: `displayInfo()`, `serialize()`, `toString()` overridden in subclasses
  - Polymorphism: Scheduleable references can hold Task, Event, or Homework objects
<img width="666" height="94" alt="Screenshot 2025-12-13 at 10 29 17 PM" src="https://github.com/user-attachments/assets/c8d11598-59a4-41d6-adfe-15f4bb72a884" />
<img width="527" height="541" alt="Screenshot 2025-12-13 at 10 30 38 PM" src="https://github.com/user-attachments/assets/caf87c5c-65df-48cb-a2dd-2607df7e405e" />
(Homework has the same attributes as tasks, along with a subject. Using the getters from task.java, outside files are able to read those parts of task.java)

## Topic 9: Files
- **Where**: TaskManager.java (saveToFile, loadFromFile methods)
- **Demonstrated**:
  - File writing: printWriter with FileWriter
  - File reading: scanner reading from File
  - File paths: Using relative paths for data persistence
  - Serialization: Custom serialize/deserialize methods for Task, Event, and Homework
<img width="681" height="430" alt="Screenshot 2025-12-13 at 10 45 43 PM" src="https://github.com/user-attachments/assets/25ca7479-421b-4104-a519-e409356cccb1" />
<img width="993" height="519" alt="Screenshot 2025-12-13 at 10 46 43 PM" src="https://github.com/user-attachments/assets/5d49d391-c2a1-4a91-a827-55aa09365863" />
<img width="1223" height="131" alt="Screenshot 2025-12-13 at 10 47 01 PM" src="https://github.com/user-attachments/assets/aee01800-c579-46b0-9398-f80ec260d796" />
(TaskManager.java sparses and writes the data in data.txt then loads it for the next app usage)

## Topic 10: JavaFX
- **Where**: UserInterface.java, ClockDisplay.java
- **Demonstrated**:
  - JavaFX Application lifecycle: `start()` method
  - UI components: TableView, TextField, TextArea, MenuButton, Dialog
  - Layouts: VBox, ToolBar
  - Event handling: Button actions with lambda expressions
  - Property binding: StringProperty, BooleanProperty, IntegerProperty for table cells
  - Scene and Stage management
  <img width="936" height="823" alt="Screenshot 2025-12-13 at 10 48 51 PM" src="https://github.com/user-attachments/assets/23d3b548-7cda-4ffc-b86e-926da1d2c2f8" />
<img width="1276" height="690" alt="Screenshot 2025-12-13 at 10 49 49 PM" src="https://github.com/user-attachments/assets/12d6cb0e-055d-41e0-bcbc-0bb33de0f12e" />


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
(Descriptive comments, variable names, error handling and messages shown throughout the code)

## Topic 12: Multithreading
- **Where**: ClockDisplay.java
- **Demonstrated**:
  - Thread creation: `Thread clockThread = new Thread(this, "ClockThread");`
  - Runnable interface: ClockDisplay implements Runnable
  - Thread lifecycle: `start()`, `stop()` methods
  - Thread Synchronization: `volatile boolean running` flag
  - Platform.runLater(): Updating JavaFX UI from background thread safely
  - Continuous execution: `run()` method that updates clock every second
<img width="841" height="919" alt="Screenshot 2025-12-13 at 10 50 58 PM" src="https://github.com/user-attachments/assets/b8829762-ebbf-4790-8f48-c02d817aaadb" />
<img width="473" height="85" alt="Screenshot 2025-12-13 at 10 51 17 PM" src="https://github.com/user-attachments/assets/a04cf6eb-c225-4b96-b1b3-97540a1e085c" />
(ClockDisplay runs its own thread so that it can constantly run without crashing the application)
