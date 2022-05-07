package com.winchesters.devopsify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
}
