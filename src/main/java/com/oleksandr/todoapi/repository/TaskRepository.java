package com.oleksandr.todoapi.repository;

import com.oleksandr.todoapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTitle(String title);
    List<Task> findByTaskDate(LocalDate taskDate);
}
