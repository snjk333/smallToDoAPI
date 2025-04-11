package com.oleksandr.todoapi.Aspects;

import com.oleksandr.todoapi.controller.TaskController;
import com.oleksandr.todoapi.dto.TaskDTO;
import com.oleksandr.todoapi.util.CustomDateFormatter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ValidationAspect {

    private static final Logger logger = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(String com.oleksandr.todoapi.controller.TaskController.getTaskByTitle(String))")
    public Object aroundGetTaskByTitleAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        var argument = joinPoint.getArgs();
        Object targetMethodResult;
        if (argument.length == 0 || argument[0] == null || argument[0].toString().isBlank()) {
            logger.warn("getTaskByTitle received empty title");
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        logger.debug("getTaskByTitle received valid argument: {}", argument[0]);
        try {
            targetMethodResult = joinPoint.proceed();
            logger.info("Successfully fetched task with title: {}", argument[0]);
        } catch (Exception e) {
            logger.error("Error while fetching task by title", e);
            throw e;
        }
        return targetMethodResult;
    }

    @Around("execution(void com.oleksandr.todoapi.controller.TaskController.saveTask(com.oleksandr.todoapi.dto.TaskDTO))")
    public void aroundSaveTaskAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        var arguments = joinPoint.getArgs();
        if (arguments.length == 0 || arguments[0] == null || !(arguments[0] instanceof TaskDTO taskDTO)) {
            logger.warn("saveTask received invalid or missing TaskDTO");
            throw new IllegalArgumentException("Invalid task data provided");
        }
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isBlank()) {
            logger.warn("saveTask received empty title in TaskDTO");
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        if (!CustomDateFormatter.isValid(taskDTO.getTaskDate())) {
            logger.warn("saveTask received invalid date: {}", taskDTO.getTaskDate());
            throw new IllegalArgumentException("Invalid task date provided");
        }
        try {
            Object result = joinPoint.proceed();
            logger.info("Successfully saved task with title: {}", taskDTO.getTitle());
        } catch (Exception e) {
            logger.error("Error while saving task", e);
            throw e;
        }
        }
    }

