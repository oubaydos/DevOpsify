package com.winchesters.devopsify.service;

import com.winchesters.devopsify.auth.AuthenticationFacade;
import com.winchesters.devopsify.dto.SignUpFormDto;
import com.winchesters.devopsify.dto.UserResponseDto;
import com.winchesters.devopsify.exception.InvalidEmailException;
import com.winchesters.devopsify.exception.InvalidUsernameException;
import com.winchesters.devopsify.mapper.EntityToDtoMapper;
import com.winchesters.devopsify.model.User;
import com.winchesters.devopsify.repository.UserRepository;
import com.winchesters.devopsify.security.ApplicationUserRole;
import com.winchesters.devopsify.security.PasswordConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;
    public UserResponseDto getUser() {
        String username = authenticationFacade.getAuthenticatedUsername();

        if(!username.equals("anonymousUser")){
            User user = userRepository.findByUsername(username)
                .orElseThrow(()->new IllegalStateException(String.format("user %s not found",username)));
            return EntityToDtoMapper.userToUserResponseDto(user);
        }
        throw new IllegalStateException("user must be authenticated");
    }

    public List<UserResponseDto> getUsers() {
        return EntityToDtoMapper.userToUserResponseDto(userRepository.findAll());
    }

    public UserResponseDto adminCreateUser(User user) {

        return EntityToDtoMapper.userToUserResponseDto(userRepository.save(user));
    }

    public UserResponseDto singUp(SignUpFormDto signUpForm) {
        userRepository.findByUsername(signUpForm.getUsername())
                .ifPresent(
                        (u)-> {
                            throw new InvalidUsernameException(String.format("username %s is already taken.",u.getUsername()));
                        });
        userRepository.findByEmail(signUpForm.getEmail())
                .ifPresent(
                        (u)-> {
                            throw new InvalidEmailException(String.format("email %s is already taken.",u.getEmail()));
                        });
        String password = signUpForm.getPassword();

        if(!PasswordConfig.isValid(password))
            throw new IllegalStateException("password must contain ..TODO.....");

        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(signUpForm.getUsername());
        //TODO: add email validation
        user.setEmail(signUpForm.getEmail());
        user.setRole(ApplicationUserRole.fromName(signUpForm.getRole()));

        return EntityToDtoMapper.userToUserResponseDto(userRepository.save(user));
    }
    @Transactional
    public void updateEmail(Long userId, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException(String.format("user with id %d not found",userId)));
        user.setEmail(email);
    }

    public Map<String,String> getAuthenticatedUser() {
        return Map.of(
                "username",authenticationFacade.getAuthenticatedUsername(),
                "role",authenticationFacade.getAuthenticatedUserRole()
        );
    }
}