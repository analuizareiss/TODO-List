package model;
import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private String name;
    private String description;
    private LocalDate dueDate;
    private int priority;
    private String category;
    private String status;

    public Task(String name, String description, LocalDate dueDate,
                int priority, String category, String status) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
        this.status = status;
    }

    @Override
    public int compareTo(Task t) {
        return Integer.compare(t.priority, this.priority);
    }


    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public int getPriority() { return priority; }
    public String getCategory() { return category; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
    public void setPriority(int priority) { this.priority = priority; }

    @Override
    public String toString() {
        return "[Nome: " + name +
                " | Desc: " + description +
                " | Data: " + dueDate +
                " | Prioridade: " + priority +
                " | Categoria: " + category +
                " | Status: " + status + "]";
    }
}
