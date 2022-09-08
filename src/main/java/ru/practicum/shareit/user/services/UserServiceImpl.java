package ru.practicum.shareit.user.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repositoryes.UserStorage;

import java.util.List;

//TODO Разобраться как правильно указывать конструкторы
@Service
@RequiredArgsConstructor
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

    @Override
    public User findByEmail(String email) throws UserNotFound {
        return userStorage.findByEmail(email);
    }
}
