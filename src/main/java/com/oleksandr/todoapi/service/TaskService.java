package com.oleksandr.todoapi.service;

import com.oleksandr.todoapi.dto.TaskDTO;

import java.util.List;

public interface TaskService {


    public List<TaskDTO> getAllTasks();

    public void saveTask(TaskDTO dto);

    public TaskDTO findByTitle(String title);

    public List<TaskDTO> findTasksByDate(String date);

    public List<TaskDTO> findTodayTasks();

}
