package com.oleksandr.todoapi.controller;

import com.oleksandr.todoapi.dto.TaskDTO;
import com.oleksandr.todoapi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.GetMapping;

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

    @ValueSource(
            strings = {"doSmth", "cookFood"}
    )
    @ParameterizedTest
    void getTaskByTitleReturnsDTO(String title) {
        var task = TaskDTO.builder()
                .title(title)
                .build();
        Mockito.doReturn(task).when(taskService).findByTitle(title);

        var actual = taskController.getTaskByTitle(title);

        assertNotNull(actual);
        assertThat(actual).isEqualTo(task);
    }

    @ValueSource(
            strings = {"28-07-2005", "22-03-2020"}
    )
    @ParameterizedTest
    void getTaskByDateReturnsDTOSuccess(String date) {
        var task = List.of(TaskDTO.builder()
                .title("testTitle")
                .taskDate(date)
                .build());
        Mockito.doReturn(task).when(taskService).findTasksByDate(date);

        var actual = taskController.getTaskByDate(date);

        assertNotNull(actual);
        assertThat(actual).isEqualTo(task);
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid", "30-02-2023"})
    void getTaskByDateReturnsEmptyList(String date) {
        var response = taskController.getTaskByDate(date);
        assertThat(response).isEqualTo(List.of());
    }

    @Test
    void getTodayTasksReturnsDTO() {
        var task = List.of(TaskDTO.builder()
                .title("testTitle")
                .taskDate("11-04-2025")
                .build());
        Mockito.doReturn(task).when(taskService).findTodayTasks();

        var actualResult = taskController.getTaskByToday();
        assertNotNull(actualResult);
        assertThat(actualResult).isEqualTo(task);
    }

    @Test
    void saveTaskCallsServiceWithCorrectDTO() {
        TaskDTO taskDTO = TaskDTO.builder()
                .title("testTitle")
                .taskDate("11-04-2025")
                .build();

        taskController.saveTask(taskDTO);

        Mockito.verify(taskService).saveTask(taskDTO);
    }


}