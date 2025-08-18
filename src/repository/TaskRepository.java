package repository;
import model.Task;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class TaskRepository {
    private static final String FILE_NAME = "tasks.csv";

    public void saveAll(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task t : tasks) {
                writer.write(
                        t.getName() + ";" +
                                t.getDescription() + ";" +
                                t.getDueDate() + ";" +
                                t.getPriority() + ";" +
                                t.getCategory() + ";" +
                                t.getStatus()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Task> loadAll() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");

                if (st.countTokens() == 6) {
                    String name = st.nextToken();
                    String description = st.nextToken();
                    String dateStr = st.nextToken();
                    String priorityStr = st.nextToken();
                    String category = st.nextToken();
                    String status = st.nextToken();

                    Task task = new Task(
                            name,
                            description,
                            LocalDate.parse(dateStr),
                            Integer.parseInt(priorityStr),
                            category,
                            status
                    );
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
