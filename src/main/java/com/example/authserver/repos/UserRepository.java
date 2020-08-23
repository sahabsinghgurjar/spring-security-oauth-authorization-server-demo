package com.example.authserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.authserver.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT DISTINCT user FROM UserEntity user " +
            "INNER JOIN FETCH user.authorities AS authorities " +
            "WHERE user.userName = :username")
    UserEntity findByUserName(@Param("username") String username);
}