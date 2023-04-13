package com.northsouthairline.projectpw.repositories;

import com.northsouthairline.projectpw.entities.ERole;
import com.northsouthairline.projectpw.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
