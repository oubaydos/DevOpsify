package com.winchesters.devopsify.dto.request;

import lombok.Data;

@Data
public class SignUpFormDto {
    private String username;
    private String email;
    private String password;
}
