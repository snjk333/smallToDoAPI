package com.oleksandr.todoapi.dto;
import java.time.LocalDate;


public record TaskDTO(String title, String description, LocalDate taskDate) {
}

