package Ahola.AholaGroup.dto;

import Ahola.AholaGroup.entity.Admin;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.stream.Collectors;

@Data
public class UsersDto {
    private Long id;
    private String name;

    private String username;

    private String phoneNumber;

    private String age;

    private String email;

    private String city;


    public UsersDto(Admin admin) {
        id = admin.getId();
        name = admin.getName();
        username = admin.getUsername();
        phoneNumber = admin.getPhoneNumber();
        age = admin.getAge();
        email = admin.getEmail();
        city = admin.getCity();
    }
}