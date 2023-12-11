package Ahola.AholaGroup.controller;

import Ahola.AholaGroup.dto.AppResponse;
import Ahola.AholaGroup.dto.AuthenticationRequest;
import Ahola.AholaGroup.dto.RegisterationRequest;
import Ahola.AholaGroup.service.authentication.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/signup")
    ResponseEntity<AppResponse<Map<String, Object>>> create_Admin(@Valid @RequestBody RegisterationRequest admin){
        return ResponseEntity.ok(authenticationService.createAdmin(admin));
    }


    @PostMapping("/login")
    public ResponseEntity<AppResponse<String>> login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

}
