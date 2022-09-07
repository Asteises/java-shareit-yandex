package ru.practicum.shareit.user.controllers;

import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.user.services.UserService;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public User save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{userId}")
    public User put(@RequestBody UserDto userDto, @PathVariable long userId) throws UserNotFound {
        return userService.put(userDto, userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable long userId) throws UserNotFound {
        userService.delete(userId);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId) throws UserNotFound {
        return userService.findById(userId);
    }
}
