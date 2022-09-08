package ru.practicum.shareit.user.repositoryes;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserDao implements UserStorage {

    private final UserMapper userMapper;

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public User save(UserDto userDto) {
        //TODO Хочется с помощью стрима проверить значение в значениях в мапе на наличие email
//        users.values().stream().collect(Collectors.toList()).stream().map(User::getEmail).findAny(userDto.getEmail()).orElse(1);
        //TODO Какую ошибку нужно выкинуть, чтобы получить 404
        if (userDto.getEmail() != null
                && !userDto.getEmail().isEmpty()
                && EmailValidator.getInstance().isValid(userDto.getEmail())) {
            for (User u: users.values()) {
                if (u.getEmail().contains(userDto.getEmail())) {
                    throw new IllegalArgumentException("Duplicated email");
                }
            }
            User user = userMapper.toUser(userDto);
            users.put(user.getId(), user);
            return user;
        } else {
            throw new IllegalArgumentException("Duplicated email");
        }
    }

    @Override
    public User put(UserDto userDto, long userId) throws UserNotFound {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            if (userDto.getEmail() != null) {
                user.setEmail(userDto.getEmail());
            }
            if (userDto.getName() != null) {
                user.setName(userDto.getName());
            }
            users.replace(userId, user);
            return user;
        } else {
            throw new UserNotFound(String.format("User %s not found", userId));
        }
    }

    @Override
    public void delete(long userId) throws UserNotFound {
        if (users.containsKey(userId)) {
            users.remove(userId, users.get(userId));
        } else {
            throw new UserNotFound(String.format("User %s not found", userId));
        }
    }

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public User findById(long userId) throws UserNotFound {
        if (users.containsKey(userId)) {
            return users.get(userId);
        } else {
            throw new UserNotFound(String.format("User %s not found", userId));
        }
    }

    @Override
    public User findByEmail(String email) throws UserNotFound {
        return null;
    }
}
