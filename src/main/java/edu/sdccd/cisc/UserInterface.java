package edu.sdccd.cisc;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserInterface extends Application {
    //create table gui and Array lists to hold data
    private TableView<ScheduleableRow> tableView;
    private ClockDisplay clockDisplay;
    private TaskManager taskManager;
    private ArrayList<Scheduleable> scheduleables = new ArrayList<>();
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    //launch
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Task Helper");

        // Initialize TaskManager and load existing data
        taskManager = new TaskManager();
        try {
            taskManager.loadFromFile();
            // Load all items from TaskManager into scheduleables
            scheduleables.addAll(taskManager.getAllTasks());
            scheduleables.addAll(taskManager.getAllEvents());
            scheduleables.addAll(taskManager.getAllHomework());
        } catch (Exception e) {
            System.out.println("No previous data to load.");
        }

        // Create the clock display
        clockDisplay = new ClockDisplay();

        //display stuff
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //for column size
        tableView.setEditable(true);          //for checkbox to check if done
        tableView.setSelectionModel(null);    //no blue highlight thing

        //columns (data, category, title and desc)
        TableColumn<ScheduleableRow, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(cell -> cell.getValue().dateTimeProperty());
        dateCol.setPrefWidth(120);

        TableColumn<ScheduleableRow, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(cell -> cell.getValue().categoryProperty());
        categoryCol.setPrefWidth(100);

        TableColumn<ScheduleableRow, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(cell -> cell.getValue().titleProperty());
        titleCol.setPrefWidth(150);

        TableColumn<ScheduleableRow, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
        descCol.setCellFactory(tc -> new TableCell<>() {
            private final Text text = new Text();

            {
                text.wrappingWidthProperty().bind(descCol.widthProperty().subtract(10));
                setGraphic(text); //shows text instead of string
            }
            //checks when cell is updated, and adds 10 px based on height of txt
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    text.setText("");
                    setPrefHeight(24);
                } else {
                    text.setText(item);
                    setPrefHeight(text.getLayoutBounds().getHeight() + 10);
                }
            }
        });
        //for priority column, minutes and checkbox for done
        TableColumn<ScheduleableRow, Number> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(cell -> cell.getValue().priorityProperty());
        priorityCol.setPrefWidth(70);

        TableColumn<ScheduleableRow, Number> minutesCol = new TableColumn<>("Est. Min");
        minutesCol.setCellValueFactory(cell -> cell.getValue().estimatedMinutesProperty());
        minutesCol.setPrefWidth(70);

        TableColumn<ScheduleableRow, Boolean> doneCol = new TableColumn<>("Done");
        doneCol.setCellValueFactory(cell -> cell.getValue().completedProperty());
        doneCol.setCellFactory(CheckBoxTableCell.forTableColumn(doneCol));
        doneCol.setPrefWidth(60);
        //adds all columns to the table
        tableView.getColumns().addAll(dateCol, categoryCol, titleCol, descCol, priorityCol, minutesCol, doneCol);

        //dropdown menu (easier to create tasks)
        MenuButton addMenu = new MenuButton("Add");
        MenuItem addTask = new MenuItem("Task");
        MenuItem addHomework = new MenuItem("Homework");
        MenuItem addEvent = new MenuItem("Event");
        addMenu.getItems().addAll(addTask, addHomework, addEvent);

        addTask.setOnAction(e -> showTaskForm());
        addHomework.setOnAction(e -> showHomeworkForm());
        addEvent.setOnAction(e -> showEventForm());

        ToolBar toolbar = new ToolBar(addMenu, clockDisplay);

        VBox layout = new VBox(10, toolbar, tableView);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 1000, 500);
        stage.setScene(scene);

        // Stop the clock when window closes
        stage.setOnCloseRequest(e -> clockDisplay.stop());

        stage.show();

        showTodayItems();
    }

    //uses localdatetime to show date of task
    private void showTodayItems() {
        tableView.getItems().clear();
        LocalDateTime today = LocalDateTime.now();
        //temp vars
        for (Scheduleable s : scheduleables) {
            LocalDateTime dt = null;
            String category = "";
            String title = "";
            String desc = "";
            byte priority = 0;
            int minutes = 0;
            //checks if hw, event or task then it will add to the table
            //for homework, subject is inputted into the description, same with event but for location
            if (s instanceof Homework hw) {
                dt = hw.getDueDate();
                category = "Homework";
                title = hw.getTitle();
                desc = hw.getTitle() + " (" + hw.getSubject().getName() + ")\n" + hw.getDescription();
                priority = hw.getPriority();
                minutes = hw.getEstimatedMinutes();
            } else if (s instanceof Event ev) {
                dt = ev.getStartDate();
                category = "Event";
                title = ev.getTitle();
                desc = ev.getTitle() + " @ " + ev.getLocation() + "\n" + ev.getDescription();
                priority = ev.getPriority();
                minutes = ev.getEstimatedMinutes();
            } else if (s instanceof Task t) {
                dt = t.getDueDate();
                category = "Task";
                title = t.getTitle();
                desc = t.getTitle() + "\n" + t.getDescription();
                priority = t.getPriority();
                minutes = t.getEstimatedMinutes();
            }

            if (dt != null && dt.toLocalDate().isEqual(today.toLocalDate())) {
                tableView.getItems().add(new ScheduleableRow(s, dt.format(fmt), category, title, desc, priority, minutes, this));
            }
        }
    }

    //opens new window for user to fill out the task they want to input
    private void showTaskForm() {
        Dialog<Task> dialog = new Dialog<>();
        dialog.setTitle("New Task");

        TextField title = new TextField();
        title.setPromptText("Title");

        TextArea desc = new TextArea();
        desc.setPromptText("Description");

        TextField priority = new TextField();
        priority.setPromptText("Priority (1-3)");

        TextField minutes = new TextField();
        minutes.setPromptText("Estimated minutes");
        //vbox with 10 px spacing, with labels and input for each
        VBox vbox = new VBox(10,
                new Label("Title:"), title,
                new Label("Description:"), desc,
                new Label("Priority:"), priority,
                new Label("Estimated Minutes:"), minutes
        );
        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        //the ok and cancel buttons
        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                Task task = new Task(
                        title.getText(),
                        desc.getText(),
                        Byte.parseByte(priority.getText()),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );
                task.setEstimatedMinutes(Integer.parseInt(minutes.getText()));
                return task;
            }
            return null;
        });
        //if tasks are inputted, it will be added to scheduleable
        dialog.showAndWait().ifPresent(task -> {
            scheduleables.add(task);
            taskManager.addTask(task);
            saveToFile();
            showTodayItems();
        });
    }
    //same thing as taskform but for homework
    private void showHomeworkForm() {
        Dialog<Homework> dialog = new Dialog<>();
        dialog.setTitle("New Homework");

        TextField title = new TextField();
        title.setPromptText("Title");

        TextArea desc = new TextArea();
        desc.setPromptText("Description");

        TextField subject = new TextField();
        subject.setPromptText("Subject");

        TextField priority = new TextField();
        priority.setPromptText("Priority (1-3)");

        TextField minutes = new TextField();
        minutes.setPromptText("Estimated minutes");

        VBox vbox = new VBox(10,
                new Label("Title:"), title,
                new Label("Description:"), desc,
                new Label("Subject:"), subject,
                new Label("Priority:"), priority,
                new Label("Estimated Minutes:"), minutes
        );
        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                Subject sub = new Subject(subject.getText());
                Homework hw = new Homework(
                        title.getText(),
                        desc.getText(),
                        LocalDateTime.now(),
                        sub
                );
                hw.setPriority(Byte.parseByte(priority.getText()));
                hw.setEstimatedMinutes(Integer.parseInt(minutes.getText()));
                return hw;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(hw -> {
            scheduleables.add(hw);
            taskManager.addHomework(hw);
            saveToFile();
            showTodayItems();
        });
    }
    //also same as taskform and hwform, but for event this time
    private void showEventForm() {
        Dialog<Event> dialog = new Dialog<>();
        dialog.setTitle("New Event");

        TextField title = new TextField();
        title.setPromptText("Title");

        TextArea desc = new TextArea();
        desc.setPromptText("Description");

        TextField location = new TextField();
        location.setPromptText("Location");

        TextField priority = new TextField();
        priority.setPromptText("Priority (1-3)");

        TextField minutes = new TextField();
        minutes.setPromptText("Estimated minutes");

        VBox vbox = new VBox(10,
                new Label("Title:"), title,
                new Label("Description:"), desc,
                new Label("Location:"), location,
                new Label("Priority:"), priority,
                new Label("Estimated Minutes:"), minutes
        );
        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                Event ev = new Event(
                        title.getText(),
                        desc.getText(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        location.getText()
                );
                ev.setPriority(Byte.parseByte(priority.getText()));
                ev.setEstimatedMinutes(Integer.parseInt(minutes.getText()));
                return ev;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(ev -> {
            scheduleables.add(ev);
            taskManager.addEvent(ev);
            saveToFile();
            showTodayItems();
        });
    }

    // Save all data to file
    private void saveToFile() {
        try {
            taskManager.saveToFile();
        } catch (Exception e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    //fields to store each row of thing when the gui updates
    public static class ScheduleableRow {
        private final Scheduleable scheduleable;
        private final StringProperty dateTime;
        private final StringProperty category;
        private final StringProperty title;
        private final StringProperty description;
        private final BooleanProperty completed;
        private final IntegerProperty priority;
        private final IntegerProperty estimatedMinutes;

        //constructor for converting data into row format
        public ScheduleableRow(Scheduleable s, String dateTime, String category, String title, String description,
                               int priority, int estimatedMinutes, UserInterface ui) {
            this.scheduleable = s;
            this.dateTime = new SimpleStringProperty(dateTime);
            this.category = new SimpleStringProperty(category);
            this.title = new SimpleStringProperty(title);
            this.description = new SimpleStringProperty(description);
            this.completed = new SimpleBooleanProperty(s instanceof Task t && t.isCompleted());
            this.completed.addListener((obs, oldVal, newVal) -> {
                if (s instanceof Task t) {
                    t.setCompleted(newVal);
                    ui.saveToFile();
                }
            });
            this.priority = new SimpleIntegerProperty(priority);
            this.estimatedMinutes = new SimpleIntegerProperty(estimatedMinutes);
        }
        //binds each column to its respective one
        public StringProperty dateTimeProperty() { return dateTime; }
        public StringProperty categoryProperty() { return category; }
        public StringProperty titleProperty() { return title; }
        public StringProperty descriptionProperty() { return description; }
        public BooleanProperty completedProperty() { return completed; }
        public IntegerProperty priorityProperty() { return priority; }
        public IntegerProperty estimatedMinutesProperty() { return estimatedMinutes; }
    }
}