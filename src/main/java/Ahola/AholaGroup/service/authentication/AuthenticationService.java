package Ahola.AholaGroup.service.authentication;

import Ahola.AholaGroup.dto.*;
import Ahola.AholaGroup.entity.Admin;
import Ahola.AholaGroup.entity.Role;
import Ahola.AholaGroup.exception.ApiException;
import Ahola.AholaGroup.repository.AdminRepository;
import Ahola.AholaGroup.repository.RoleRepository;
import Ahola.AholaGroup.service.JwtService;
import Ahola.AholaGroup.service.MyUserDetailsService;
import Ahola.AholaGroup.service.email.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationServiceImpl {

    private final AdminRepository adminRepo;
    private final RoleRepository roleRepository ;

    private final MyUserDetailsService myUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final EmailServiceImpl emailService;


    @Override
    public AppResponse<Map<String, Object>> createAdmin(RegisterationRequest request){
        boolean check = adminRepo.existsByUsernameOrEmail(request.getUsername(), request.getEmail());
        if (check) throw new ApiException("User Already Exists, Login to Continue");

        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setAge(request.getAge());
        admin.setCity(request.getCity());

        admin.setPhoneNumber(request.getPhoneNumber());

        admin.setRoles(create_Role());

        Admin userSaved = adminRepo.save(admin);


        //send email alert
        EmailDto emailDto = EmailDto.builder()
                .recipient(userSaved.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("Congratulation " + request.getUsername() + " your Account has been created")
                .build();

        emailService.sendEmailAlert(emailDto);

        return new AppResponse<>(0, "Admin successfully created", Map.of(
                "id", admin.getId(),
                "username", admin.getUsername(),
                "email", admin.getEmail()));


    }

    @Override
    public AppResponse<String> login(AuthenticationRequest authenticationRequest) {

        var user = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()))
            return  new AppResponse<>(0,"wrong gmail/password");

        var jwtToken = jwtService.generateToken(user);


        return  new AppResponse<>(0,"Successfully logged in", jwtToken);

    }


    public Role create_Role (){
        Role role = roleRepository.findByName("ADMIN");

        if (role == null) {
            Role role1  = new Role();
            role1.setName("ADMIN");
            return role1;
        }
        return role;
    }

}
