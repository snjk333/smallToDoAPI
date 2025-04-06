package com.oleksandr.todoapi.repository;

import com.oleksandr.todoapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
