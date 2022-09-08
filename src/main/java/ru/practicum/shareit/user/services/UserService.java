package ru.practicum.shareit.user.services;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
public interface UserService {

    User save(UserDto userDto);

    User put(UserDto userDto, long userId) throws UserNotFound;

    void delete(long userId) throws UserNotFound;

    List<User> findAll();

    User findById(long userId) throws UserNotFound;

    User findByEmail(String email) throws UserNotFound;
}
