package com.winchesters.devopsify.controller.user;

import com.winchesters.devopsify.dto.SignUpFormDto;
import com.winchesters.devopsify.dto.UserResponseDto;
import com.winchesters.devopsify.model.entity.User;
import com.winchesters.devopsify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path ="users")
    public List<UserResponseDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping
    public UserResponseDto getUser(){
        return userService.getUser();
    }

    @PostMapping(headers={"target=adminCreateUser"})
    public UserResponseDto adminCreateUser(@RequestBody User user){
        return userService.adminCreateUser(user);
    }

    @PostMapping(path = {"signup"})
    public UserResponseDto signUp(@RequestBody SignUpFormDto signupForm){
        return userService.singUp(signupForm);
    }

    @RequestMapping(method = RequestMethod.PUT, path="{userId}",headers={"target=updateEmail"})
    public void updateEmail(@PathVariable Long userId,@RequestBody String email){
        userService.updateEmail(userId, email);
    }
}