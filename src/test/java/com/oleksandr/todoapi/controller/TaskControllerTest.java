package com.oleksandr.todoapi.controller;

import com.oleksandr.todoapi.dto.TaskDTO;
import com.oleksandr.todoapi.entity.Task;
import com.oleksandr.todoapi.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    TaskService taskService;

    @InjectMocks
    TaskController taskController;

    @Test
    void getAllTasksReturnsDTO() {
        var tasks = List.of(new TaskDTO( "Task 1", "Task 1", "10-04-2025"),
                new TaskDTO("Task 2", "Task 2", "10-04-2025"));
        Mockito.doReturn(tasks).when(taskService).getAllTasks();


        var actual = taskController.getAllTasks();

        assertNotNull(actual);
        assertThat(actual).isEqualTo(tasks);
    }

}