package com.petadoption.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petadoption.backend.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}