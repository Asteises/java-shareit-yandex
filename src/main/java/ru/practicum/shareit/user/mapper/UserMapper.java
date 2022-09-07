package ru.practicum.shareit.user.mapper;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.Random;

@Service
public class UserMapper {

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail()
        );
    }

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setId(new Random().nextLong());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}