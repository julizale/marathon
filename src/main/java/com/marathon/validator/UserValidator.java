package com.marathon.validator;

import com.marathon.domain.User;
import com.marathon.exception.FieldMustNotBeNullException;
import com.marathon.exception.NotValidEmailAddressException;
import com.marathon.exception.NotValidNameException;
import com.marathon.exception.UserWithGivenEmailExistsException;
import com.marathon.service.UserDbService;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private UserDbService userService;

    public void validateUser(User user) throws Exception {
        validateEmail(user);
    }

    private void validateEmail(User user) throws Exception {
        if (user.getEmail() == null) {
            throw new FieldMustNotBeNullException("Email field must not be null");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            throw new NotValidEmailAddressException("This is not valid email address.");
        }
        if (userService.getAllUsers().stream().anyMatch(u -> u.getEmail().equals(user.getEmail()) && !Objects.equals(user.getId(), u.getId()))) {
            throw new UserWithGivenEmailExistsException();
        }
    }

    private void validateName(String name) throws NotValidNameException{
        Pattern pattern = Pattern.compile("^[^- '](?=(?!\\p{Lu}?\\p{Lu}))(?=(?!\\p{Ll}+\\p{Lu}))(?=(?!.*\\p{Lu}\\p{Lu}))(?=(?!.*[- '][- '.]))(?=(?!.*[.][-'.]))(\\p{L}|[- '.]){2,}$");
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            throw new NotValidNameException("Name should be 3-25 characters containing only letters and \'-\'");
        }
    }

}
