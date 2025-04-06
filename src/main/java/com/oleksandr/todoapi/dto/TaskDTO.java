package com.oleksandr.todoapi.dto;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TaskDTO(String title, String description, String taskDate) {
}

