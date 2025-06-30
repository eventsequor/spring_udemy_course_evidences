package com.eventsequor.jpa.execise;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public void addTask(String description) {
        taskList.add(new Task(Status.PENDING, description));
    }


    public void maskAsACompleted(String taskId) {
        Task task = taskList.stream().filter(t -> t.getId().equalsIgnoreCase(taskId)).findFirst().orElse(null);
        if (task == null)
            return;
        task.setStatus(Status.COMPLETED);
    }

    public void listAllTask() {
        taskList.forEach(System.out::println);
    }

    public Task getTaskByDescription(String description) {
        return taskList.stream().filter(t -> t.getDescription().equalsIgnoreCase(description)).findFirst().orElse(null);
    }

    public void listAllTaskByStatus(Status status) {
        taskList.stream().filter(t -> t.getStatus().equals(status)).forEach(System.out::println);
    }
}
