package com.example.ResumeAnalyxer.Controller;

import com.example.ResumeAnalyxer.DTOs.LoginRequest;
import com.example.ResumeAnalyxer.DTOs.LoginResponse;
import com.example.ResumeAnalyxer.DTOs.RegisterRequest;
import com.example.ResumeAnalyxer.Model.User;
import com.example.ResumeAnalyxer.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User Management", description = "APIs for user registration and authentication")
public class UserController {
    @Autowired
    UserService userService;
//ADDING USER
    @PostMapping("user/add")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    //Retriving all User
    @GetMapping("user/allUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    //Deleting User By ID
    @DeleteMapping("user/delete/{userId}")
    public String deleteUserById(@PathVariable int userId){
        return userService.deleteUserById(userId);
    }
    //Update User By Id
    @PutMapping("user/update/{userId}")
    public String updateUserById(@PathVariable int userId,
                                 @RequestBody User updatedUser){
        return userService.updateUserById(userId,updatedUser);
    }
    //Get USER by Id
    @GetMapping("user/byID/{userId}")
    public User getUserById(@PathVariable int userId){
        return userService.getUserById(userId);
    }

    //User REgister
    @Operation(
            summary = "Register User",
            description = "Creates a new user account."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("user/register")
    public User userRegister(@RequestBody RegisterRequest registerRequest){

        return userService.userRegister(registerRequest);
    }

    //User login
    @Operation(
            summary = "Login User",
            description = "Authenticates the user and returns a JWT token."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid password"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("user/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
}
