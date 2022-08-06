package com.example.webdoctruyen.Service;

import com.example.webdoctruyen.Entity.UserEntity;
import com.example.webdoctruyen.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    public List<UserEntity> getall(Pageable pageable){
        return  userRepo.findAll(pageable).getContent();
    }
    public UserEntity create(UserEntity user){
        UserEntity userEntity = userRepo.findUserEntityByEmail(user.getEmail());
        if(userEntity != null) return new UserEntity();
        return  userRepo.save(user);
    }
    public String deleteById(int id){
        userRepo.deleteById(id);
        return "Thanh Cong";
    }
    public UserEntity getUserByEmail(String email){
        return userRepo.findUserEntityByEmail(email);
    }
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
