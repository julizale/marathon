package com.marathon.controller;

import com.marathon.domain.User;
import com.marathon.domain.dto.UserDto;
import com.marathon.exception.UserNotFoundException;
import com.marathon.exception.UserWithGivenEmailExistsException;
import com.marathon.mapper.UserMapper;
import com.marathon.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserDbService userDbService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers () {
        List<UserDto> userDtos = userMapper.mapToUserDtoList(userDbService.getAllUsers());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) throws UserNotFoundException {
        UserDto userDto = userMapper.mapToUserDto(userDbService.getUser(id));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser (@RequestBody UserDto userDto) throws UserWithGivenEmailExistsException {
        User user = userMapper.mapToUser(userDto);
        userDbService.save(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser (@RequestBody UserDto userDto) throws UserWithGivenEmailExistsException {
        User user = userMapper.mapToUser(userDto);
        User updatedUser = userDbService.save(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(updatedUser));
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userDbService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
