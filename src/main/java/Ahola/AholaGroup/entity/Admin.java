package Ahola.AholaGroup.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "city", nullable = false)
    private String city;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Role roles;
}
