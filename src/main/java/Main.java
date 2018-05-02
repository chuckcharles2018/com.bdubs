import com.google.gson.Gson;

import java.util.Comparator;
import java.util.*;
import java.io.*;

class Task implements Comparable<Task> {
    private String name;
    private String description;
    private int priority;

    static class NameComparator implements Comparator {
        public int compare(Object o1,Object o2){
            Task s1=(Task)o1;
            Task s2=(Task)o2;

            return s1.name.compareTo(s2.name);
        }
    }

    Task(String name, String description, int priority) {
        setName(name);
        setDescription(description);
        setPriority(priority);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        if (priority < 0) {
            this.priority = 0;
        } else if (priority > 5) {
            this.priority = 5;
        } else {
            this.priority = priority;
        }
    }
    private void displayTasks(List<Task> tasks)
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            Task task = (Task)tasks.get(i);
            System.out.println("Task index: " + i + ", Name: " + task
                    .getName() + ", Description: " + task
                    .getDescription() + ", Priority: " + task
                    .getPriority());
        }
    }

    @Override
    public int compareTo(Task compareTask) {

        int compareQuantity = ((Task) compareTask).getPriority();

        //ascending order
        return this.priority - compareQuantity;

        //descending order
        //return compareQuantity - this.quantity;

    }

    public static Comparator<Task> TaskNameComparator
            = new Comparator<Task>() {

        public int compare(Task task1, Task task2) {

            String taskName1 = task1.getName().toUpperCase();
            String taskName2 = task2.getName().toUpperCase();

            //ascending order
            return taskName1.compareTo(taskName2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };
}


class TaskCollection
{
    private List<Task> tasks;

    TaskCollection()
    {
        this.tasks = new LinkedList();
    }

    private Task createTask(String name, String description, int priority)
    {
        return new Task(name, description, priority);
    }

    public void addTask(String name, String description, int priority)
    {
        this.tasks.add(createTask(name, description, priority));
    }

    public void removeTask(int index)
    {
        if (this.tasks.size() > index) {
            this.tasks.remove(index);
        }
    }

    public void updateTask(int index, String name, String description, int priority)
    {
        this.tasks.set(index, createTask(name, description, priority));
    }

    public List<Task> getTasks()
    {
        return this.tasks;
    }

    public List<Task> getTasks(int priorty)
    {
        List<Task> matchedTasks = new LinkedList();
        for (Task task : this.tasks) {
            if (task.getPriority() == priorty) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}

class TaskView {
    private TaskCollection taskCollection;
    private Scanner scanner = new Scanner(System.in);

    TaskView(TaskCollection collection) {
        this.taskCollection = collection;
    }

    private boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private String prompt(String message) {
        System.out.println(message);
        return this.scanner.nextLine();
    }

    private int promptInt(String message) {
        String response = prompt(message);

        boolean isInt = isInteger(response);
        while (!isInt) {
            response = prompt(message);
            isInt = isInteger(response);
        }
        return Integer.parseInt(response);
    }

    private void add() {
        String name = prompt("What is the new task's name?");
        String description = prompt("What is the new task's description?");
        int priority = promptInt("What is the new task's priority?");
        this.taskCollection.addTask(name, description, priority);
    }

    private void remove() {
        int index = promptInt("What is the index of the task you want to remove?");
        this.taskCollection.removeTask(index);
    }

    private void update(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = (Task) tasks.get(i);
            System.out.println("Task index: " + i + ", Name: " + task
                    .getName() + ", Description: " + task
                    .getDescription() + ", Priority: " + task
                    .getPriority());
        }
        int index = promptInt("What is the index of the task you want to update.");
        String newName = prompt("Enter the new name of the task.");
        String newDescription = prompt("Enter the new Description of the task");
        int newPriority = promptInt("Enter the new priority of the task");
        this.taskCollection.updateTask(index, newName, newDescription, newPriority);
    }

    private void displayTasks(List<Task> tasks) {
        List priority1 = new ArrayList();
        List priority2 = new ArrayList();
        List priority3 = new ArrayList();
        List priority4 = new ArrayList();
        List priority5 = new ArrayList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = (Task) tasks.get(i);
            int priority = task.getPriority();
            String name = task.getName();
            String description = task.getDescription();
            int index = i;
            if (priority == 1) {
                priority1.add(task);
                this.taskCollection.updateTask(index, name, description, priority);
                Collections.sort(priority1, new Task.NameComparator());
            }
            if (priority == 2) {
                priority2.add(task);
                this.taskCollection.updateTask(index, name, description, priority);
                Collections.sort(priority2, new Task.NameComparator());
            }
            if (priority == 3) {
                priority3.add(task);
                this.taskCollection.updateTask(index, name, description, priority);
                Collections.sort(priority3, new Task.NameComparator());
            }
            if (priority == 4) {
                priority4.add(task);
                this.taskCollection.updateTask(index, name, description, priority);
                Collections.sort(priority4, new Task.NameComparator());
            }
            if (priority == 5) {
                priority5.add(task);
                this.taskCollection.updateTask(index, name, description, priority);
                Collections.sort(priority5, new Task.NameComparator());
            }
        }
        for (int i = 0; i < priority1.size(); i++) {
            Task task = (Task)priority1.get(i);
            System.out.println("Name: " + task.getName()  + ", Description: " + task.getDescription() + ", Priority: 1" );
        }
        for (int i = 0; i < priority2.size(); i++) {
            Task task = (Task)priority2.get(i);
            System.out.println("Name: " + task.getName()  + ", Description: " + task.getDescription() + ", Priority: 2" );
        }
        for (int i = 0; i < priority3.size(); i++) {
            Task task = (Task)priority3.get(i);
            System.out.println("Name: " + task.getName()  + ", Description: " + task.getDescription() + ", Priority: 3" );
        }
        for (int i = 0; i < priority4.size(); i++) {
            Task task = (Task)priority4.get(i);
            System.out.println("Name: " + task.getName()  + ", Description: " + task.getDescription() + ", Priority: 4" );
        }
        for (int i = 0; i < priority5.size(); i++) {
            Task task = (Task)priority5.get(i);
            System.out.println("Name: " + task.getName()  + ", Description: " + task.getDescription() + ", Priority: 5" );
        }
    }









    private void list()
    {
        displayTasks(this.taskCollection.getTasks());
    }

    private void listByPriority()
    {
        int priority = promptInt("Enter a priority");
        displayTasks(this.taskCollection.getTasks(priority));
    }

    private void menu()
    {
        boolean endLoop = false;
        while (!endLoop)
        {
            System.out.println("Menu");
            System.out.println("1) Add a task.");
            System.out.println("2) Remove a task.");
            System.out.println("3) Update a task.");
            System.out.println("4) List all tasks.");
            System.out.println("5) List all tasks of a certain priority.");
            System.out.println("0) Exit");

            int userInput = promptInt("Choose an option.");
            switch (userInput)
            {
                case 0:
                    endLoop = true;
                    break;
                case 1:
                    add();
                    break;
                case 2:
                    remove();
                    break;
                case 3:
                    update(this.taskCollection.getTasks());
                    break;
                case 4:
                    list();
                    break;
                case 5:
                    listByPriority();
            }
        }
    }

    public void run()
    {
        menu();
    }
}

public class Main {
    static final String FILENAME = "data.json";

    public static TaskCollection load(String filename) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filename);
        try {
            return gson.fromJson(reader, TaskCollection.class);
        }
        finally {
            reader.close();
        }
    }


    public static void save(String filename, TaskCollection data) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(filename);
        try {
            gson.toJson(data, writer);
        }
        finally {
            writer.close();
        }
    }

    public static void main(String[] args) {
        try{
        TaskCollection collection = load(FILENAME);
        TaskView view = new TaskView(collection);
        view.run();
        save(FILENAME, collection);

    }catch (IOException e){
            e.printStackTrace();
        }
    }
}