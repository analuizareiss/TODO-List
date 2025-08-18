import model.Task;
import service.TaskService;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskService service = new TaskService();

        while (true) {
            System.out.println("\n===== TODO LIST =====");
            System.out.println("1 - Criar Tarefa");
            System.out.println("2 - Listar Todas");
            System.out.println("3 - Listar por Categoria");
            System.out.println("4 - Listar por Prioridade");
            System.out.println("5 - Listar por Status");
            System.out.println("6 - Remover Tarefa");
            System.out.println("7 - Atualizar Tarefa");
            System.out.println("8 - Atualizar Somente Status");
            System.out.println("9 - Consultar número de tarefas por status");
            System.out.println("10 - Listar por Data de Término");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:{
                    System.out.print("Nome: ");
                    String name = sc.nextLine();
                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();

                    LocalDate date = null;
                    while (date == null) {
                        System.out.print("Data término (AAAA-MM-DD): ");
                        String inputDate = sc.nextLine();
                        try {
                            date = LocalDate.parse(inputDate);
                        } catch (Exception e) {
                            System.out.println("Formato de data inválido! Por favor, digite no formato AAAA-MM-DD.");
                        }
                    }

                    System.out.print("Prioridade (1-5): ");
                    int prio = sc.nextInt(); sc.nextLine();
                    System.out.print("Categoria: ");
                    String cat = sc.nextLine();
                    System.out.print("Status (ToDo/Doing/Done): ");
                    String status = sc.nextLine();
                    service.addTask(new Task(name, desc, date, prio, cat, status));
                    break;
                }
                case 2: {
                    service.getAll().forEach(System.out::println);
                    break;
                }
                case 3: {
                    System.out.print("Categoria: ");
                    String cat = sc.nextLine();
                    service.filterByCategory(cat).forEach(System.out::println);
                    break;
                }
                case 4: {
                    System.out.print("Prioridade: ");
                    int prio = sc.nextInt(); sc.nextLine();
                    service.filterByPriority(prio).forEach(System.out::println);
                    break;
                }
                case 5: {
                    System.out.print("Status: ");
                    String status = sc.nextLine();
                    service.filterByStatus(status).forEach(System.out::println);
                    break;
                }
                case 6: {
                    System.out.print("Nome da tarefa a remover: ");
                    String name = sc.nextLine();
                    service.removeTask(name);
                    break;
                }
                case 7: {
                    System.out.print("Nome da tarefa que deseja atualizar: ");
                    String currentName = sc.nextLine();
                    boolean exists = false;
                    for (Task t : service.getAll()) {
                        if (t.getName().equalsIgnoreCase(currentName)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        System.out.println("Tarefa não encontrada!");
                        break;
                    }
                    System.out.println("Informe os novos dados da tarefa:");
                    System.out.print("Novo Nome: ");
                    String newName = sc.nextLine();
                    System.out.print("Nova Descrição: ");
                    String newDesc = sc.nextLine();

                    LocalDate newDate = null;
                    while (newDate == null) {
                        System.out.print("Nova Data término (AAAA-MM-DD): ");
                        String dateInput = sc.nextLine();
                        try {
                            newDate = LocalDate.parse(dateInput);
                        } catch (Exception e) {
                            System.out.println("Formato inválido. Por favor, informe AAAA-MM-DD.");
                        }
                    }
                    System.out.print("Nova Prioridade (1-5): ");
                    int newPrio = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nova Categoria: ");
                    String newCat = sc.nextLine();

                    System.out.print("Novo Status (ToDo/Doing/Done): ");
                    String newStatus = sc.nextLine();

                    Task updatedTask = new Task(newName, newDesc, newDate, newPrio, newCat, newStatus);
                    boolean updated = service.updateTask(currentName, updatedTask);

                    if (updated) {
                        System.out.println("Tarefa atualizada com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar tarefa.");
                    }
                    break;
                }
                case 8: {
                    boolean statusUpdated = false;
                    while (!statusUpdated) {
                        System.out.print("Nome da tarefa para atualizar o status: ");
                        String taskName = sc.nextLine();

                        System.out.print("Novo status (ToDo/Doing/Done): ");
                        String newStatus = sc.nextLine();

                        statusUpdated = service.updateStatus(taskName, newStatus);

                        if (!statusUpdated) {
                            System.out.println("Tarefa não encontrada. Por favor, tente novamente.");
                        } else {
                            System.out.println("Status atualizado com sucesso!");
                        }
                    }
                    break;
                }

                case 9:{
                    Map<String, Integer> counts = service.countByStatus();
                    System.out.println("Número de tarefas por status:");
                    System.out.println("ToDo: " + counts.get("ToDo"));
                    System.out.println("Doing: " + counts.get("Doing"));
                    System.out.println("Done: " + counts.get("Done"));
                    break;
                }
                case 10: {
                    System.out.print("Data de término para filtro (AAAA-MM-DD): ");
                    LocalDate filterDate = LocalDate.parse(sc.nextLine());
                    service.filterByDueDate(filterDate).forEach(System.out::println);
                    break;
                }

                case 0: {
                    System.out.println("Saindo...");
                    return;
                }
                default: System.out.println("Opção inválida!");
            }
            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine();
        }
    }
}
