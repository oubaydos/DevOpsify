package com.winchesters.devopsify.dto;

import lombok.Data;

@Data
public class SignUpFormDto {
    private String username;
    private String email;
    private String role;
    private String password;
}
