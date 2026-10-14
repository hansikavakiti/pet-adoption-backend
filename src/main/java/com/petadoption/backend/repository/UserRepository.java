package com.petadoption.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petadoption.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}