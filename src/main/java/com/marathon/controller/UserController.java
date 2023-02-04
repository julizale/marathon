package com.marathon.controller;

import com.marathon.domain.User;
import com.marathon.domain.dto.UserDto;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addUser (@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.save(user);
        return ResponseEntity.ok(user.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser (@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userDbService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
