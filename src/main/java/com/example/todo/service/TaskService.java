package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {@Autowired
private TaskRepository taskRepository;

    public List<Task> getTasksByUserId(UUID userId) {
        return taskRepository.findByUsuarioId(userId);
    }

    public Task addTask(Task tarea) {
        return taskRepository.save(tarea);
    }

    public Task markTaskAsResolved(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        task.setStatus(true);
        return taskRepository.save(task);
    }

    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}


