package com.winchesters.devopsify.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String role;
}
