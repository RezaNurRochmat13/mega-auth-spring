package com.mega.auth.module.user.repository;

import com.mega.auth.module.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.deletedAt IS NULL")
    List<User> findAllActiveUsers();
    Optional<User> findByUsername(String username);
}
