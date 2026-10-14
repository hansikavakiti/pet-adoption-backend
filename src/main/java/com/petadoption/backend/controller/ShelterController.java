package com.petadoption.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petadoption.backend.entity.Shelter;
import com.petadoption.backend.repository.ShelterRepository;

@RestController
@RequestMapping("/shelters")
@CrossOrigin
public class ShelterController {

    private final ShelterRepository shelterRepository;

    public ShelterController(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    // Add shelter
    @PostMapping
    public Shelter addShelter(@RequestBody Shelter shelter) {

        shelter.setApprovalStatus("Pending");

        return shelterRepository.save(shelter);
    }

    // Get all shelters
    @GetMapping
    public List<Shelter> getAllShelters() {

        return shelterRepository.findAll();
    }

    // Get shelter by ID
    @GetMapping("/{id}")
    public Shelter getShelterById(@PathVariable Long id) {

        return shelterRepository.findById(id).orElse(null);
    }

    // Update shelter
    @PutMapping("/{id}")
    public Shelter updateShelter(
            @PathVariable Long id,
            @RequestBody Shelter shelter) {

        Shelter existingShelter =
                shelterRepository.findById(id).orElseThrow();

        existingShelter.setName(shelter.getName());
        existingShelter.setEmail(shelter.getEmail());
        existingShelter.setPhone(shelter.getPhone());
        existingShelter.setAddress(shelter.getAddress());

        return shelterRepository.save(existingShelter);
    }

    // Approve or reject shelter
    @PutMapping("/{id}/status")
    public Shelter updateApprovalStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Shelter shelter =
                shelterRepository.findById(id).orElseThrow();

        shelter.setApprovalStatus(status);

        return shelterRepository.save(shelter);
    }

    // Delete shelter
    @DeleteMapping("/{id}")
    public String deleteShelter(@PathVariable Long id) {

        shelterRepository.deleteById(id);

        return "Shelter deleted successfully";
    }

    
    @GetMapping("/user/{userId}")
    public Shelter getShelterByUserId(
        @PathVariable Long userId) {

        return shelterRepository.findByUserId(userId)
            .orElse(null);
    }

    
}