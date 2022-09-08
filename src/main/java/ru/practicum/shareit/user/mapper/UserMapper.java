package ru.practicum.shareit.user.mapper;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

@Service
public class UserMapper {

    private static long id = 0;

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail()
        );
    }

    public User toUser(UserDto userDto) {
        User user = new User();
        id++;
        user.setId(id);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
