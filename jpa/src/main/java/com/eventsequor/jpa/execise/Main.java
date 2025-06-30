package com.eventsequor.jpa.execise;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        taskManager.addTask("task 1");
        taskManager.addTask("task 2");
        taskManager.addTask("task 3");
        taskManager.addTask("task 4");
        taskManager.addTask("task 5");

        System.out.println("First list without modification");
        taskManager.listAllTask();
        System.out.println("\n\n");

        // identifying one task
        Task task = taskManager.getTaskByDescription("task 2");

        // Marking task as a completed
        taskManager.maskAsACompleted(task.getId());

        // Show again the task
        taskManager.listAllTask();

        System.out.println("\n\nPrint only task PENDING");
        taskManager.listAllTaskByStatus(Status.PENDING);
    }
}
