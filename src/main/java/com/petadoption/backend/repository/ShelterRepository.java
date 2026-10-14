
package com.petadoption.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petadoption.backend.entity.Shelter;

public interface ShelterRepository
        extends JpaRepository<Shelter, Long> {

    Optional<Shelter> findByUserId(Long userId);
}