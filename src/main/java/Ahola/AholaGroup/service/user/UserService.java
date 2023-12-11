package Ahola.AholaGroup.service.user;

import Ahola.AholaGroup.dto.AppResponse;
import Ahola.AholaGroup.dto.UsersDto;
import Ahola.AholaGroup.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImpl {
    private final AdminRepository adminRepo;

    @Override
    public AppResponse<Map<String, Object>> getAllUsers(Pageable pageable) {
        // Converting quizQuestion too QuestionDto loop it and returned it as DTO
        Page<UsersDto> users = adminRepo.findAll(pageable).map(UsersDto::new);

        // Map.of takes up to 10 keys and values
        Map<String, Object> page = Map.of(
                "page", users.getNumber(),
                "totalPages", users.getTotalPages(),
                "totalElements", users.getTotalElements(),
                "size", users.getSize(),
                "content", users.getContent()
        );

        return new AppResponse<>("success", page);

    }
}
