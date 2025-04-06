package com.taskmanager.api.controller;

import com.taskmanager.api.dto.TaskDTO;
import com.taskmanager.api.entity.Task;
import com.taskmanager.api.service.TaskService;
import com.taskmanager.api.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // Create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestHeader("Authorization") String authHeader, @RequestBody TaskDTO dto) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Task task = taskService.createTask(username, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    // Get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        List<Task> tasks = taskService.getUserTasks(username);
        return ResponseEntity.ok(tasks);
    }

    // Get a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Task task = taskService.getTaskById(username, id);
        return ResponseEntity.ok(task);
    }

    // Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody TaskDTO dto) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Task updatedTask = taskService.updateTask(username, id, dto);
        return ResponseEntity.ok(updatedTask);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@RequestHeader("Authorization") String authHeader, @PathVariable Long id) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        taskService.deleteTask(username, id);
        return ResponseEntity.noContent().build();
    }
}

