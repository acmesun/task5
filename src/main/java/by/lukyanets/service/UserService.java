package by.lukyanets.service;

import by.lukyanets.dto.UserDto;
import by.lukyanets.entity.UserEntity;

public interface UserService {
    UserEntity registerNewUserAccount(UserDto userDto);
}
