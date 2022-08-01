package com.example.webdoctruyen.Service;

import com.example.webdoctruyen.Entity.UserEntity;
import com.example.webdoctruyen.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findUserEntityByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("username not found");
        }
        SimpleGrantedAuthority grand = new SimpleGrantedAuthority(userEntity.roleEntity.getName());
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();


        authorities.add(grand);
        User user = new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
        return user;
    }
}
