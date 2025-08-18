package service;
import model.Task;
import repository.TaskRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.*;

public class TaskService {
    private List<Task> tasks;
    private TaskRepository repository;

    public TaskService() {
        repository = new TaskRepository();
        tasks = repository.loadAll();
        Collections.sort(tasks);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        Collections.sort(tasks);
        repository.saveAll(tasks);
    }

    public void removeTask(String name) {
        tasks.removeIf(t -> t.getName().equalsIgnoreCase(name));
        repository.saveAll(tasks);
    }

    public List<Task> getAll() {
        return tasks;
    }

    public List<Task> filterByCategory(String category) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getCategory().equalsIgnoreCase(category)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Task> filterByPriority(int priority) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority() == priority) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Task> filterByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getStatus().equalsIgnoreCase(status)) {
                result.add(t);
            }
        }
        return result;
    }

    public boolean updateTask(String currentName, Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getName().equalsIgnoreCase(currentName)) {
                tasks.set(i, updatedTask);
                Collections.sort(tasks);
                repository.saveAll(tasks);
                return true;
            }
        }
        return false;
    }

    public boolean updateStatus(String taskName, String newStatus) {
        for (Task t : tasks) {
            if (t.getName().equalsIgnoreCase(taskName)) {
                t.setStatus(newStatus);
                repository.saveAll(tasks);
                return true;
            }
        }
        return false;
    }



    public Map<String, Integer> countByStatus() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("ToDo", 0);
        counts.put("Doing", 0);
        counts.put("Done", 0);

        for (Task t : tasks) {
            String status = t.getStatus();
            if (counts.containsKey(status)) {
                counts.put(status, counts.get(status) + 1);
            }
        }
        return counts;
    }

    public List<Task> filterByDueDate(LocalDate date) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDueDate().equals(date)) {
                result.add(t);
            }
        }
        return result;
    }

}

