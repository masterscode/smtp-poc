package Ahola.AholaGroup.service.user;

import Ahola.AholaGroup.dto.AppResponse;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserServiceImpl {
    AppResponse<Map<String, Object>> getAllUsers(Pageable pageable);

}
