package by.lukyanets.service;

import by.lukyanets.dto.UserDto;
import by.lukyanets.dto.UserManagementDto;
import by.lukyanets.entity.UserEntity;

import java.util.Collection;
import java.util.List;

public interface UserService {
    UserEntity registerNewUserAccount(UserDto userDto);
    void deleteUserAccounts(Collection<String> emailsToBlock);

    void blockUserAccounts(Collection<String> emailsToBlock);
    void unblockUserAccounts(Collection<String> emailsToUnblock);

    List<UserManagementDto> listAllUsers();
}
