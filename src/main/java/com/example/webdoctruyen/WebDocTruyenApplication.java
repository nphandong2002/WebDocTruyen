package com.example.webdoctruyen;

import com.example.webdoctruyen.Entity.RoleEntity;
import com.example.webdoctruyen.Entity.UserEntity;
import com.example.webdoctruyen.Service.RoleService;
import com.example.webdoctruyen.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebDocTruyenApplication {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;
    public static void main(String[] args) {
        SpringApplication.run(WebDocTruyenApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            RoleEntity admin = new RoleEntity("admin");
            roleService.create(admin);
            RoleEntity qtv = new RoleEntity("qtv");
            roleService.create(qtv);

            RoleEntity userR = new RoleEntity("user");
            roleService.create(userR);

            UserEntity user = new UserEntity();
            user.setEmail("admin");
            user.setPassword("$2a$12$u5r/Aev7zk0L6VAkxM6d6.PhQlDaS1TvwTqLcyqkZm1swPHYq7yk2"); //admin
            user.setExp(10000);
            user.setName("Admin");
            user.setRole_id(1);
            userService.create(user);
        };
    }
}
