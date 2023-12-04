package ru.Raingor.webAnimeSite.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    @NotEmpty(message = "The name must not be empty")
    @Size(min = 2
            , max = 200
            , message = "The name must have between two letters and two hundred letters in it")
    private String name;
    @NotEmpty(message = "The password must not be empty")
    private String password;
    @Email
    private String email;
}
