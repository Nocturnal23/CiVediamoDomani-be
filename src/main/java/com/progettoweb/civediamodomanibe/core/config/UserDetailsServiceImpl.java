package com.progettoweb.civediamodomanibe.core.config;

import com.progettoweb.civediamodomanibe.entity.UserAccount;
import com.progettoweb.civediamodomanibe.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userRepository.findByUsername(username);
        if(userAccount != null) return new User(username, userAccount.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        throw new UsernameNotFoundException("User not found");
    }
}
