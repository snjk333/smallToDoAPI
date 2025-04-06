package com.oleksandr.todoapi.mapper;

import com.oleksandr.todoapi.dto.TaskDTO;
import com.oleksandr.todoapi.entity.Task;
import com.oleksandr.todoapi.formatter.CustomDateFormatter;

public class TaskMapper {
    CustomDateFormatter formatter = new CustomDateFormatter();
    public TaskDTO mapToDTO(Task task) {
        return TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .taskDate(formatter.format(task.getTaskDate()))
                .build();
    }
}
