package com.oleksandr.todoapi.mapper;

import com.oleksandr.todoapi.dto.TaskDTO;
import com.oleksandr.todoapi.entity.Task;
import com.oleksandr.todoapi.util.CustomDateFormatter;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDTO mapToDTO(Task task) {
        return TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .taskDate(CustomDateFormatter.formatToString(task.getTaskDate()))
                .build();
    }

    public Task mapToEntity(TaskDTO dto) {
        return Task.builder()
                .title(dto.title())
                .description(dto.description())
                .taskDate(CustomDateFormatter.formatToDate(dto.taskDate()))
                .build();
    }
}
