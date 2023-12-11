package Ahola.AholaGroup.service.authentication;

import Ahola.AholaGroup.dto.AppResponse;
import Ahola.AholaGroup.dto.AuthenticationRequest;
import Ahola.AholaGroup.dto.RegisterationRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AuthenticationServiceImpl {
     AppResponse<Map<String, Object>> createAdmin(RegisterationRequest request);
     AppResponse<String> login(AuthenticationRequest authenticationRequest);
}

