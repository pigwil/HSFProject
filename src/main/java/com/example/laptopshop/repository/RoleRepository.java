package com.example.laptopshop.repository;

import com.example.laptopshop.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    public Role findByRoleName(String roleName);
}
