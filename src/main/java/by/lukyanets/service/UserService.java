package by.lukyanets.service;

import by.lukyanets.dto.UserDto;
import by.lukyanets.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity registerNewUserAccount(UserDto userDto);
    void deleteUserAccounts(List<UserDto> userDtoList);
    void blockUserAccount(UserDto userDto);
}
