package com.oleksandr.todoapi.contoller;

import com.oleksandr.todoapi.dto.TaskDTO;
import com.oleksandr.todoapi.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public TaskDTO getTaskByTitle(@RequestParam String title) {
        return taskService.findByTitle(title);
    }

    @GetMapping("/date")
    public List<TaskDTO> getTaskByDate(@RequestParam String date) {
        return taskService.findTasksByDate(date);
    }

    @GetMapping("/today")
    public List<TaskDTO> getTaskByToday() {
        return taskService.findTodayTasks();
    }

    @PostMapping
    public void saveTask(@RequestBody TaskDTO taskDTO) {
        taskService.saveTask(taskDTO);
    }

}
