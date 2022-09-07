package ru.practicum.shareit.user.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repositoryes.UserStorage;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserStorage userStorage;

    @Override
    public User save(UserDto userDto) {
        return userStorage.save(userDto);
    }

    @Override
    public User put(UserDto userDto, long userId) throws UserNotFound {
        return userStorage.put(userDto, userId);
    }

    @Override
    public void delete(long userId) throws UserNotFound {
        userStorage.delete(userId);
    }

    @Override
    public List<User> findAll() {
        return userStorage.findAll();
    }

    @Override
    public User findById(long userId) throws UserNotFound {
        return userStorage.findById(userId);
    }
}
