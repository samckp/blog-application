package com.sam.blog.controllers;

import com.sam.blog.entities.User;
import com.sam.blog.payloads.UserDto;
import com.sam.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto>  createUser(@RequestBody UserDto userDto){

       UserDto createuserDto = userService.createUser(userDto);
       return new ResponseEntity<>(createuserDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userid){

            UserDto userDto1 = this.userService.updateUser(userDto, userid);
            return ResponseEntity.ok(userDto1);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userid){

        this.deleteUser(userid);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

}
