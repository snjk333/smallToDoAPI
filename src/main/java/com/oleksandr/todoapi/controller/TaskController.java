package com.oleksandr.todoapi.controller;

import com.oleksandr.todoapi.dto.TaskDTO;
import com.oleksandr.todoapi.service.TaskService;
import com.oleksandr.todoapi.util.CustomDateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TaskController {
    private final TaskService taskService;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        logger.info("Fetching all tasks");
        return taskService.getAllTasks();
    }

    @GetMapping("/title")
    public TaskDTO getTaskByTitle(@RequestParam String title) {
        return taskService.findByTitle(title);
    }

    @GetMapping("/date")
    public List<TaskDTO> getTaskByDate(@RequestParam String date) {
        try {
            logger.info("Fetching tasks for date: {}", date);
            return taskService.findTasksByDate(date);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid date format: {}", date);
            return List.of();
        }
    }


    @GetMapping("/today")
    public List<TaskDTO> getTaskByToday() {
        logger.info("Fetching tasks for today");
        return taskService.findTodayTasks();
    }

    @PostMapping
    public void saveTask(@RequestBody TaskDTO taskDTO) {
        try {
            logger.info("Saving task: {}", taskDTO);
            taskService.saveTask(taskDTO);
        } catch (IllegalArgumentException e) {
            logger.error("Error saving task: Invalid data");
        } catch (Exception e) {
            logger.error("Error saving task: {}", e.getMessage());
        }
    }

}
