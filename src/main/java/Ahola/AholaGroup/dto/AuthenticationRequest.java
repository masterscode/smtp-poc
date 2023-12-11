package Ahola.AholaGroup.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthenticationRequest {
    @NotEmpty(message = "Password cannot be empty")
    private String password;


    @NotEmpty(message = "Email cannot be empty")
    private String email;

}
