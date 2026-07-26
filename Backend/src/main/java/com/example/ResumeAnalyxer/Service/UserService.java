package com.example.ResumeAnalyxer.Service;

import com.example.ResumeAnalyxer.DTOs.LoginRequest;
import com.example.ResumeAnalyxer.DTOs.LoginResponse;
import com.example.ResumeAnalyxer.DTOs.RegisterRequest;
import com.example.ResumeAnalyxer.Model.User;
import com.example.ResumeAnalyxer.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public String addUser(User user) {
        user.setCreatedDate(LocalDateTime.now());
        userRepo.save(user);
        return user.getUserID()+" created Sucessfully";
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public String deleteUserById(int userId) {
        userRepo.deleteById(userId);
        return " Used Deleted with Id "+userId;
    }

    public String updateUserById(int userId, User updatedUser) {
       User user1= userRepo.findById(userId).get();
       user1.setEmail(updatedUser.getEmail());
       user1.setName(updatedUser.getName());
       userRepo.save(user1);
       return userId+" updated Sucessfully";

    }

    public User getUserById(int userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public User userRegister(RegisterRequest registerRequest) {
        User user= new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreatedDate(LocalDateTime.now());
        //validation
        Optional<User> existingUser= userRepo.findByEmail(registerRequest.getEmail());
        if(existingUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User already exist");
        }
        return userRepo.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> user1 = userRepo.findByEmail(loginRequest.getEmail());
        if(user1.isEmpty()){
            throw new RuntimeException("invalid Email");
        }
        User existingUser = user1.get();
        existingUser.getName();
        existingUser.getEmail();
        existingUser.getPassword();
        if(!existingUser.getPassword().equals(loginRequest.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        LoginResponse loginResponse= new LoginResponse();
        loginResponse.setUserId(existingUser.getUserID());
        loginResponse.setEmail(existingUser.getEmail());
        loginResponse.setName(existingUser.getName());
        loginResponse.setMessage("Login Sucessfull");
        return loginResponse;
    }
}
