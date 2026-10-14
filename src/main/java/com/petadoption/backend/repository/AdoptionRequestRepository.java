package com.petadoption.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petadoption.backend.entity.AdoptionRequest;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {

}