package ru.practicum.shareit.user.repositoryes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Getter
@Setter
@RequiredArgsConstructor
public class UserDao implements UserStorage {

    private UserMapper userMapper;

    private Map<Long, User> users = new HashMap<>();

    @Override
    public User save(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        getUsers().put(user.getId(), user);
        return user;
    }

    @Override
    public User put(UserDto userDto, long userId) throws UserNotFound {
        if (getUsers().containsKey(userId)) {
            User user = userMapper.toUser(userDto);
            getUsers().replace(userId, user);
            return user;
        } else {
            throw new UserNotFound(String.format("User %s not found", userId));
        }
    }

    @Override
    public void delete(long userId) throws UserNotFound {
        if (getUsers().containsKey(userId)) {
            getUsers().remove(userId);
        } else {
            throw new UserNotFound(String.format("User %s not found", userId));
        }
    }

    @Override
    public List<User> findAll() {
        return getUsers().values().stream().toList();
    }

    @Override
    public User findById(long userId) throws UserNotFound {
        if (getUsers().containsKey(userId)) {
            return getUsers().get(userId);
        } else {
            throw new UserNotFound(String.format("User %s not found", userId));
        }
    }
}
