package com.oleksandr.todoapi.service;

public interface TaskService {

    public void saveTask(TaskDTO dto);

    public TaskDTO findByTitle(String title);

    public List<TaskDTO> findTasksByDate(String date);

    public List<TaskDTO> findTodayTasks();

}
