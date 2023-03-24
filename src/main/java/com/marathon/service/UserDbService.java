package com.marathon.service;

import com.marathon.domain.User;
import com.marathon.exception.UserNotFoundException;
import com.marathon.exception.ValidationException;
import com.marathon.repository.UserRepository;
import com.marathon.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserDbService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;

    public User save(User user) throws ValidationException {
        log.info("Validating user data before saving to database...");
        try {
            userValidator.validateUser(user);
        } catch (Exception e) {
            log.error("Validation exception: ", e);
            throw new ValidationException(e.getMessage());
        }
        log.info("User saved successfully.");
        return userRepository.save(user);
    }

    public User getUser(long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
