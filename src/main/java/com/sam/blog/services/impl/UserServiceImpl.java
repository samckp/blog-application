package com.sam.blog.services.impl;

import com.sam.blog.entities.User;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.payloads.UserDto;
import com.sam.blog.repositories.UserRepository;
import com.sam.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User sevedUser = this.userRepository.save(user);
        return this.userToDto(sevedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {

        User user = this.userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",id)
        );

        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepository.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getByUserId(Integer id) {

        User user = this.userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",id)
        );
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepository.findAll();

        List<UserDto> usersdto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return usersdto;
    }

    @Override
    public void deleteuser(Integer id) {

       User user = this.userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",id)
        );
       this.userRepository.delete(user);
    }

    public User dtoToUser(UserDto userDto){

        User user = this.modelMapper.map(userDto, User.class);

       /* user.setId(userDto.getId());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());*/
        return user;
    }

    public UserDto userToDto(User user){

        UserDto userDto =  this.modelMapper.map(user,UserDto.class);
        /*userDto.setId(user.getId());
        userDto.setAbout(user.getAbout());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());*/
        return userDto;
    }
}
