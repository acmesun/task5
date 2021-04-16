package by.lukyanets.service.impl;

import by.lukyanets.dto.UserDto;
import by.lukyanets.dto.UserManagementDto;
import by.lukyanets.entity.UserEntity;
import by.lukyanets.repository.UserRepository;
import by.lukyanets.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserEntity registerNewUserAccount(UserDto userDto) {
        if (repository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("There is an account with that email address " + userDto.getEmail());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(encoder.encode(userDto.getPassword()));
        userEntity.setEmail(userDto.getEmail());
        userEntity.setDateOfRegistration(new Date());
        userEntity.setName(userDto.getName());
        return repository.save(userEntity);
    }

    public void deleteUserAccounts(Collection<String> emailsToDelete) {
        List<UserEntity> users = repository.findAllByEmailIn(emailsToDelete);
        for (UserEntity user : users) {
            repository.delete(user);
        }
    }

    @Override
    public void blockUserAccounts(Collection<String> emailsToBlock) {
        List<UserEntity> users = repository.findAllByEmailIn(emailsToBlock);
        for (UserEntity user : users) {
            user.setActive(false);
        }
        repository.saveAll(users);
    }

    @Override
    public void unblockUserAccounts(Collection<String> emailsToUnblock) {
        List<UserEntity> users = repository.findAllByEmailIn(emailsToUnblock);
        for (UserEntity user : users) {
            user.setActive(true);
        }
        repository.saveAll(users);
    }

    @Override
    public List<UserManagementDto> listAllUsers() {
        List<UserManagementDto> list = new ArrayList<>();
        List<UserEntity> fromDb = repository.findAll();
        for (UserEntity userEntity : fromDb) {
            UserManagementDto userManagementDto = new UserManagementDto();
            userManagementDto.setId(userEntity.getId());
            userManagementDto.setName(userEntity.getName());
            userManagementDto.setEmail(userEntity.getEmail());
            userManagementDto.setDateOfRegistration(userEntity.getDateOfRegistration());
            userManagementDto.setDateOfLastLogin(userEntity.getDateOfLastLogin());
            userManagementDto.setActive(userEntity.isActive());
            list.add(userManagementDto);
        }
        return list;
    }
}
