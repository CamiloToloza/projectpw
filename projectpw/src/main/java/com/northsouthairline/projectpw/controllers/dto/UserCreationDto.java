package com.northsouthairline.projectpw.controllers.dto;

import lombok.Data;

@Data
public class UserCreationDto {

    private  String username;
    private String email;
    private String password;
}
