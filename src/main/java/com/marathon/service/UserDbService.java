package com.marathon.service;

import com.marathon.domain.User;
import com.marathon.exception.UserNotFoundException;
import com.marathon.exception.UserWithGivenEmailExistsException;
import com.marathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDbService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) throws UserWithGivenEmailExistsException {
        if (user.getId() == 0 && getAllUsers().stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new UserWithGivenEmailExistsException();
        }
        return userRepository.save(user);
    }

    public User getUser(long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
