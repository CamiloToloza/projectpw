package com.northsouthairline.projectpw.controllers.dto.mapping;

import com.northsouthairline.projectpw.controllers.dto.UserCreationDto;
import com.northsouthairline.projectpw.controllers.dto.UserDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.UserUpdateDto;
import com.northsouthairline.projectpw.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserCreationDto toCreationDto(User user){

        UserCreationDto userCreationDto = new UserCreationDto();
        //BeanUtils.copyProperties(userCreationDto, user);
        userCreationDto.setUsername(user.getUsername());
        userCreationDto.setEmail(user.getEmail());
        userCreationDto.setPassword(user.getPassword());

        return userCreationDto;
    }

    public User toCreationEntity(UserCreationDto userCreationDto){

        User user = new User();
        //BeanUtils.copyProperties(userCreationDto, user);
        user.setUsername(userCreationDto.getUsername());
        user.setEmail(userCreationDto.getEmail());
        user.setPassword(userCreationDto.getPassword());

        return user;
    }

    public User toDetailsEntity(UserDetailsDto userDetailsDto){

        User user = new User();
        //BeanUtils.copyProperties(userCreationDto, user);
        user.setUsername(userDetailsDto.getUsername());
        user.setEmail(userDetailsDto.getEmail());

        return user;
    }

    public UserDetailsDto toDetailsDto(User user){

        UserDetailsDto userDetailsDto = new UserDetailsDto();
        //BeanUtils.copyProperties(userCreationDto, user);
        userDetailsDto.setUsername(user.getUsername());
        userDetailsDto.setEmail(user.getEmail());

        return userDetailsDto;
    }

    public User toUpdateEntity(UserUpdateDto userUpdateDto){

        User user = new User();
        //BeanUtils.copyProperties(userCreationDto, user);
        user.setUsername(userUpdateDto.getUsername());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());

        return user;
    }

    public UserUpdateDto toUpdateDto(User user){

        UserUpdateDto userUpdateDto = new UserUpdateDto();
        //BeanUtils.copyProperties(userCreationDto, user);
        userUpdateDto.setUsername(user.getUsername());
        userUpdateDto.setEmail(user.getEmail());
        userUpdateDto.setPassword(user.getPassword());

        return userUpdateDto;
    }
}
