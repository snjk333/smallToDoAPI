package com.oleksandr.todoapi.util;

import lombok.experimental.UtilityClass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.oleksandr.todoapi.util.CustomDateFormatter.formatToDate;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomDateFormatterTest {

    @Test
    void formatToStringTest(){
        LocalDate date = LocalDate.of(2000,12,1);
        String expectedResult = "01-12-2000";

        String actualResult = CustomDateFormatter.formatToString(date);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void StringDateHasValidFormatTest(){
        String dateAsString = "01-12-2000";

        boolean actualResult = CustomDateFormatter.isValid(dateAsString);

        assertThat(actualResult).isTrue();
    }

    @Test
    void StringDateHasInvalidFormatTest(){
        String dateAsString = "01-12-2000 15:12";

        boolean actualResult = CustomDateFormatter.isValid(dateAsString);

        assertThat(actualResult).isFalse();
    }


    @Test
    void formatToDateSuccessTest(){
        String dateAsString = "01-12-2000";
        LocalDate expectedResult = LocalDate.of(2000,12,1);

        LocalDate actualResult = formatToDate(dateAsString);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void shouldThrowExceptionIfDataInvalid(){
        String dateAsString = "01-12-2000 15:12";

        assertThrows(DateTimeParseException.class, () -> formatToDate(dateAsString));
    }
}
