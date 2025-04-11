package com.oleksandr.todoapi.Aspects;

import com.oleksandr.todoapi.dto.TaskDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ValidationAspect {


    @Around("execution(String com.oleksandr.todoapi.controller.TaskController.getTaskByTitle(String))")
    public Object aroundGetTaskByTitleAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        var argument = joinPoint.getArgs();
        Object targetMethodResult = null;
        if(argument.length > 0 && argument[0] != null && !argument[0].toString().isBlank()){
            try {
                targetMethodResult = joinPoint.proceed();
            }
            catch (Exception e){
                throw e;
            }
        }

        return targetMethodResult;
    }

    @Around("execution(void com.oleksandr.todoapi.controller.TaskController.saveTask(com.oleksandr.todoapi.dto.TaskDTO))") public void aroundSaveTaskAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        var argument = joinPoint.getArgs();
        if(argument.length > 0 && argument[0] != null && argument[0] instanceof TaskDTO){
            try {
                joinPoint.proceed();
            }
            catch (Exception e){
                throw e;
            }
        }
    }

}
