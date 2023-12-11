package Ahola.AholaGroup.service;

import Ahola.AholaGroup.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AdminRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map( user ->
                        new User(user.getEmail(),
                                user.getPassword(),
                                Collections.singletonList(
                                        new SimpleGrantedAuthority(user.getRoles().getName())
                                )))

                .orElseThrow(()-> new UsernameNotFoundException(email + " NOT FOUND" ));
    }
}