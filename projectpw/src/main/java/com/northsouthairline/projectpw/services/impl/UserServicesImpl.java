package com.northsouthairline.projectpw.services.impl;

import com.northsouthairline.projectpw.controllers.dto.UserCreationDto;
import com.northsouthairline.projectpw.controllers.dto.UserDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.UserUpdateDto;
import com.northsouthairline.projectpw.controllers.dto.mapping.UserMapper;
import com.northsouthairline.projectpw.entities.User;
import com.northsouthairline.projectpw.exceptions.user.UserCreationException;
import com.northsouthairline.projectpw.exceptions.user.UserNotFoundException;
import com.northsouthairline.projectpw.repositories.UserRepository;
import com.northsouthairline.projectpw.services.UserServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User create(UserCreationDto userCreationDto) {
        try {
            return userRepository.save(userMapper.toCreationEntity(userCreationDto));
        } catch (Exception exception) {//DataAccessException
            throw new UserCreationException();
        }
    }

    @Override
    public Optional<User> update(Long id, UserUpdateDto userUpdateDto) {
        return userRepository.findById(id)
                .map(userInDB -> {
                    BeanUtils.copyProperties(userUpdateDto, userInDB);
                    return Optional.of(userRepository.saveAndFlush(userInDB));
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<UserDetailsDto> findAll() {
        return userRepository.findAll()
                .stream().map(userMapper::toDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDetailsDto> findByUsername(String username) {
        return Optional.of(userRepository.findUserByUsername(username)
                .map(userMapper::toDetailsDto)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("this user doesn't exists -> %s", username))));
    }

    @Override
    public Optional<UserDetailsDto> findByEmail(String email) {
        return Optional.of(userRepository.findAll().stream().filter(u -> u.getEmail().equals(email))
                .findFirst().map(userMapper::toDetailsDto)
                .orElseThrow(() -> new NoSuchElementException(String.format(
                        "this user doesn't exists -> %s", email))));
    }

    @Override
    public Optional<UserDetailsDto> findById(Long id) {
        return Optional.of(userRepository.findById(id)
                .map(userMapper::toDetailsDto)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("this user doesn't exists -> %s", id))));
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception exception) {//EmptyResultDataAccessException
            throw new UserNotFoundException(id);
        }
    }
}
