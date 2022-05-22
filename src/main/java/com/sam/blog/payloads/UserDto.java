package com.sam.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min=4, message = "user name must be 4 char or above !")
    private String name;
    @NotEmpty
    @Email(message = "Email id is not valid !")
    private String email;
    @NotEmpty
    @Size(min=5, max = 22, message = "password must be more than 5 char !")
    private String password;
    @NotEmpty
    private String about;
}
