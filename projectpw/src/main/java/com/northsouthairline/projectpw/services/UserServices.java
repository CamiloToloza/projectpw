package com.northsouthairline.projectpw.services;

import com.northsouthairline.projectpw.controllers.dto.UserCreationDto;
import com.northsouthairline.projectpw.controllers.dto.UserDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.UserUpdateDto;
import com.northsouthairline.projectpw.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserServices {
    User create(UserCreationDto userCreationDto);
    Optional<User> update(Long id, UserUpdateDto userUpdateDto);
    List<UserDetailsDto> findAll();
    Optional<UserDetailsDto> findByUsername(String username);
    Optional<UserDetailsDto> findByEmail(String email);
    Optional<UserDetailsDto> findById(Long id);
    void delete(Long id);

    UserDetails loadUserByUsername(String username);

    org.springframework.security.core.userdetails.User getUserById(Long id);

    org.springframework.security.core.userdetails.User createUser(org.springframework.security.core.userdetails.User user);
}
