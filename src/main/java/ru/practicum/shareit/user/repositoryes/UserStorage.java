package ru.practicum.shareit.user.repositoryes;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Repository
public interface UserStorage {

    User save(UserDto userDto);

    User put(UserDto userDto, long userId) throws UserNotFound;

    void delete(long userId) throws UserNotFound;

    List<User> findAll();

    User findById(long userId) throws UserNotFound;
}
