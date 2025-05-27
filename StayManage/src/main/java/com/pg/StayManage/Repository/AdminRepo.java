package com.pg.StayManage.Repository;

import com.pg.StayManage.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
