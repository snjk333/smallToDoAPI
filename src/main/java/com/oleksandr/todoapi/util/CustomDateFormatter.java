package com.oleksandr.todoapi.util;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@UtilityClass
public class CustomDateFormatter {

    private static final String PATTERN = "dd-MM-yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDate formatToDate(String dateAsString) {
        return LocalDate.parse(dateAsString, FORMATTER);
    }

    public static boolean isValid(String dateAsString) {
        try {
            LocalDate.parse(dateAsString, FORMATTER);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public static String formatToString(LocalDate date) {
        return FORMATTER.format(date);
    }
}
