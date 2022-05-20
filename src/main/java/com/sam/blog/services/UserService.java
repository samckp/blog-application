package com.sam.blog.services;

import com.sam.blog.entities.User;
import com.sam.blog.payloads.UserDto;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer id);
    UserDto getByUserId(Integer id);
    List<UserDto> getAllUsers();
    void deleteuser(Integer id);

}
