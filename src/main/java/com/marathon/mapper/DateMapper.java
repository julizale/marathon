package com.marathon.mapper;

import com.marathon.exception.ValidationException;
import com.marathon.validator.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateMapper {

    @Autowired
    private DateValidator dateValidator;

    public LocalDate mapToLocalDate(String date) throws ValidationException {
        if (!dateValidator.validateLocalDateFormat(date)) {
            throw new ValidationException("Date should be formatted yyyy-mm-dd");
        }
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (Exception e) {
            throw new ValidationException("This is not a proper date.");
        }
        return localDate;
    }
}
