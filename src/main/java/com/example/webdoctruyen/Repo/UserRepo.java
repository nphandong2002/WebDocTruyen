package com.example.webdoctruyen.Repo;

import com.example.webdoctruyen.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    UserEntity findUserEntityByEmail(String name);


}
