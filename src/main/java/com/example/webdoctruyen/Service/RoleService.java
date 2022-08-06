package com.example.webdoctruyen.Service;

import com.example.webdoctruyen.Entity.RoleEntity;
import com.example.webdoctruyen.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;
    public List<RoleEntity> getAll(){
        return roleRepo.findAll();
    }

    public RoleEntity create(RoleEntity role){
        RoleEntity role1 = getRoleByName(role.getName());
        if(role1 != null) return new RoleEntity();
        return roleRepo.save(role);
    }
    public RoleEntity getRoleByName(String name){
        return roleRepo.findRoleEntityByName(name);
    }
    public void delete(int id){
         roleRepo.deleteById(id);
    }
}
