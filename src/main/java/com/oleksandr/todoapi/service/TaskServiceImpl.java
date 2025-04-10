package com.oleksandr.todoapi.service;

import com.oleksandr.todoapi.dto.TaskDTO;
import com.oleksandr.todoapi.entity.Task;
import com.oleksandr.todoapi.mapper.TaskMapper;
import com.oleksandr.todoapi.repository.TaskRepository;
import com.oleksandr.todoapi.util.CustomDateFormatter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    TaskMapper taskMapper;
    TaskRepository taskRepository;

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> taskMapper.mapToDTO(task)).toList();
    }

    public TaskServiceImpl(TaskMapper taskMapper, TaskRepository taskRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public void saveTask(TaskDTO dto) {
        Task task = taskMapper.mapToEntity(dto);
        taskRepository.save(task);
    }

    @Override
    public TaskDTO findByTitle(String title) {
        Task task = taskRepository.findByTitle(title);
        return taskMapper.mapToDTO(task);
    }

    @Override
    public List<TaskDTO> findTasksByDate(String date) {
        List<Task> taskList = taskRepository.findByTaskDate(CustomDateFormatter.formatToDate(date));
        return taskList.stream().map(taskMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findTodayTasks() {
        List<Task> taskList = taskRepository.findByTaskDate(LocalDate.now());
        return taskList.stream().map(taskMapper::mapToDTO).collect(Collectors.toList());
    }
}
