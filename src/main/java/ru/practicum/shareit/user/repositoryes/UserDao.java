package ru.practicum.shareit.user.repositoryes;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserDao implements UserStorage {

    private final UserMapper userMapper;

    private final Map<Long, User> users = new HashMap<>();
    private static long userID = 0;

    @Override
    public User save(UserDto userDto) {
        //TODO Хочется с помощью стрима проверить значение в значениях в мапе на наличие email
        //TODO Какую ошибку нужно выкинуть, чтобы получить 404
        if (userDto.getEmail() != null
                && !userDto.getEmail().isEmpty()
                && EmailValidator.getInstance().isValid(userDto.getEmail())) {
            //TODO Ищем существующего пользователя с таким же имейл
            User checkUser =
                    users.values().stream()
                            .filter(user -> user.getEmail().equals(userDto.getEmail()))
                            .findFirst()
                            .orElse(null);
            for (User u: users.values()) {
                if (u.getEmail().contains(userDto.getEmail())) {
                    throw new IllegalArgumentException("Duplicated email");
                }
            }
            User user = userMapper.toUser(userDto);
            user.setId(++userID);
            users.put(user.getId(), user);
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public User put(UserDto userDto, long userId) throws UserNotFound {
        if (userDto != null && users.containsKey(userId)) {
            User checkUser =
                    users.values().stream()
                            .filter(user -> user.getEmail().equals(userDto.getEmail()))
                            .findFirst()
                            .orElse(null);
            if (checkUser == null) {
                User user = users.get(userId);
                user.setId(userId);
                if (userDto.getName() != null) {
                    user.setName(userDto.getName());
                }
                if (userDto.getEmail() != null) {

                    user.setEmail(userDto.getEmail());
                }
                users.replace(userId, user);
                return user;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
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
