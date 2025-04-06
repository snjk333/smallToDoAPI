package com.oleksandr.todoapi.service;

public interface TaskService {

    public void saveTask(TaskRequestDTO dto);

    public TaskResponseDTO findByTitle(String title);

    public List<TaskResponseDTO> findTasksByDate(String date);

    public List<TaskResponseDTO> findTodayTasks();

}
