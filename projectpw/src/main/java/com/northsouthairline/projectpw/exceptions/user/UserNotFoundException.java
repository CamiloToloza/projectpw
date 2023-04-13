package com.northsouthairline.projectpw.exceptions.user;

import com.northsouthairline.projectpw.exceptions.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long id) {
        super(String.format("User not found with id: %d", id));
    }
}
