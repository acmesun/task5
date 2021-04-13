package by.lukyanets.service.impl;

import by.lukyanets.dto.UserDto;
import by.lukyanets.entity.UserEntity;
import by.lukyanets.repository.UserRepository;
import by.lukyanets.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        return repository.save(userEntity);
    }
}
