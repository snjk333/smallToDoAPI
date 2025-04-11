package com.oleksandr.todoapi.mapper;

import com.oleksandr.todoapi.dto.TaskDTO;
import com.oleksandr.todoapi.entity.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class TaskMapperTest {

    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    void mapFromEntityToDTO() {

        Task task = Task.builder()
                .title("testTitle")
                .description("testDescription")
                .taskDate(LocalDate.of(2025,4,6))
                .build();

        TaskDTO actual = taskMapper.mapToDTO(task);

        TaskDTO expectedResult = TaskDTO.builder()
                .title("testTitle")
                .description("testDescription")
                .taskDate("06-04-2025")
                .build();

        assertThat(actual).isEqualTo(expectedResult);
    }

    @Test
    void mapFromDTOToEntity() {
        TaskDTO dto = TaskDTO.builder()
                .title("testTitle")
                .description("testDescription")
                .taskDate("06-04-2025")
                .build();

        Task actualResult = taskMapper.mapToEntity(dto);


        Task expected = Task.builder()
                .title("testTitle")
                .description("testDescription")
                .taskDate(LocalDate.of(2025,4,6))
                .build();

        assertThat(actualResult).isEqualTo(expected);
    }
}