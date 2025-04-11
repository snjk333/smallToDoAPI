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

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Around("execution(String com.oleksandr.todoapi.controller.TaskController.getTaskByTitle(String))")
    public Object aroundGetTaskByTitleAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        var argument = joinPoint.getArgs();
        Object targetMethodResult;
        if(argument.length > 0 && argument[0] != null && !argument[0].toString().isBlank()){
            logger.debug("getTaskByTitle method receive valid argument");
            try {
                targetMethodResult = joinPoint.proceed();
            }
            catch (Exception e){
                logger.error("Error while GetTaskByTitle", e);
                throw e;
            }
        }
        else {
            logger.warn("getTaskByTitle method receive empty title");
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        logger.info("Successfully fetched task with title: {}", argument[0]);
        return targetMethodResult;
    }

    @Around("execution(void com.oleksandr.todoapi.controller.TaskController.saveTask(com.oleksandr.todoapi.dto.TaskDTO))")
    public void aroundSaveTaskAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        var argument = joinPoint.getArgs();
        if(argument.length > 0 && argument[0] != null && argument[0] instanceof TaskDTO){

            TaskDTO taskDTO = (TaskDTO) argument[0];
            if(!CustomDateFormatter.isValid(taskDTO.getTaskDate())){
                logger.warn("SaveTask method receive invalid DTO date");
                throw new IllegalArgumentException("Invalid task data provided");
            }
            if(taskDTO.getTitle().isEmpty()){
                logger.warn("SaveTask method receive empty DTO title");
                throw new IllegalArgumentException("Invalid task title provided");
            }
                try {
                    joinPoint.proceed();
                }
                catch (Exception e){
                    logger.error("Error while saving task", e);
                    throw e;
                }
            logger.info("Successfully saved task");
        }
    }

}
