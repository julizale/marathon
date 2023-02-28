package com.marathon.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DateValidator {

    public boolean validateLocalDateFormat(String date) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public boolean isOneYearFromNow(LocalDate date) {
        return date.isAfter(LocalDate.now().minusDays(1)) && date.isBefore(date.plusYears(1));
    }
}
