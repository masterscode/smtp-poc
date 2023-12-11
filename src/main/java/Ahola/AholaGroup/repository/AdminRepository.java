package Ahola.AholaGroup.repository;

import Ahola.AholaGroup.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

//    Email findByEmail(String)
    Optional<Admin> findByEmail(String email);

    boolean existsByUsernameOrEmail(String username, String email);

}
