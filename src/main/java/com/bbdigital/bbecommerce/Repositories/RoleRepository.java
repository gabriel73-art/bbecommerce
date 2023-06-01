package com.bbdigital.bbecommerce.Repositories;

import java.util.Optional;

import com.bbdigital.bbecommerce.Models.ERole;
import com.bbdigital.bbecommerce.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
