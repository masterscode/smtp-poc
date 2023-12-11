package Ahola.AholaGroup.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterationRequest {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Phone_Number cannot be empty")
    private String phoneNumber;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Age cannot be empty")
    private String age;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "City cannot be empty")
    private String city;
}
