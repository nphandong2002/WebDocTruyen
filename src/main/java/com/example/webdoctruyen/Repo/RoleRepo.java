package com.example.webdoctruyen.Repo;

import com.example.webdoctruyen.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity,Integer> {

    RoleEntity findRoleEntityByName(String name);
}
