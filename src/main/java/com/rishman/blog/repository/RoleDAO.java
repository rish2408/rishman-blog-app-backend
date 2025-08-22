package com.rishman.blog.repository;

import com.rishman.blog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Integer> {

}
