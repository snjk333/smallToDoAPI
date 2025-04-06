package com.oleksandr.todoapi.dto;
import lombok.Builder;


@Builder
public record TaskDTO(String title, String description, String taskDate) {
}

