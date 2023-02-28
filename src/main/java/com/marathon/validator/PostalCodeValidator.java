package com.marathon.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PostalCodeValidator {

    public boolean validatePostalCode(String postalCode) {
        Pattern pattern = Pattern.compile("\\d{2}-\\d{3}");
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }
}
