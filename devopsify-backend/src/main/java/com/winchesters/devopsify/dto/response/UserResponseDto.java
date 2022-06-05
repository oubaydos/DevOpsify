package com.winchesters.devopsify.dto.response;

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
