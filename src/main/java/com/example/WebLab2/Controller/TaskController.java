package com.example.WebLab2.Controller;

import com.example.WebLab2.Dto.TaskDto;
import com.example.WebLab2.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<TaskDto> createTask(@PathVariable Long projectId, @RequestBody TaskDto newTask) {
        return new ResponseEntity<TaskDto>(taskService.createTask(projectId, newTask), HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.readTaskByProjectIdAndTaskId(projectId, taskId));
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.readAllTaskByProject(projectId));
    }

    @PutMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskDto upTask) {
        return ResponseEntity.ok(taskService.updateTask(projectId, taskId, upTask));
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteTask(projectId, taskId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{projectId}/tasks")
    public ResponseEntity deleteCompletedTasks(@PathVariable Long projectId) {
        taskService.deleteCompletedTasksByProject(projectId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
