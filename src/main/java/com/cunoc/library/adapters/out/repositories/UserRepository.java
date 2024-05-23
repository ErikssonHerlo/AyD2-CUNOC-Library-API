package com.cunoc.library.adapters.out.repositories;

import com.cunoc.library.adapters.out.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<UserEntity, String>{
    @Query(
            value = "SELECT * FROM user u WHERE u.username = :username",
            nativeQuery = true)
    Optional<UserEntity> findUserByUsername(String username);

    @Query(
            value = "SELECT * FROM user u WHERE u.role = :roleName AND u.full_name LIKE %:name%",
            nativeQuery = true)
    List<UserEntity> getUserByRoleAndName(@Param("roleName") String roleName, @Param("name") String name);


    @Query(
            value = "SELECT * FROM user u WHERE u.role = :roleName",
            nativeQuery = true)
    List<UserEntity> getUsersByRole(@Param("roleName") String roleName);

    boolean existsByCareerCode(String careerCode);

}
