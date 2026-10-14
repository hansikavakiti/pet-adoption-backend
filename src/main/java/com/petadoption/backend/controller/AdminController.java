package com.petadoption.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petadoption.backend.entity.AdoptionRequest;
import com.petadoption.backend.entity.Pet;
import com.petadoption.backend.entity.Shelter;
import com.petadoption.backend.entity.User;
import com.petadoption.backend.repository.AdoptionRequestRepository;
import com.petadoption.backend.repository.PetRepository;
import com.petadoption.backend.repository.ShelterRepository;
import com.petadoption.backend.repository.UserRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    private final UserRepository userRepository;
    private final ShelterRepository shelterRepository;
    private final PetRepository petRepository;
    private final AdoptionRequestRepository adoptionRequestRepository;

    public AdminController(
            UserRepository userRepository,
            ShelterRepository shelterRepository,
            PetRepository petRepository,
            AdoptionRequestRepository adoptionRequestRepository) {

        this.userRepository = userRepository;
        this.shelterRepository = shelterRepository;
        this.petRepository = petRepository;
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get all shelters
    @GetMapping("/shelters")
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    // Get all pets
    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Get all adoption requests
    @GetMapping("/adoption-requests")
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestRepository.findAll();
    }

    // Delete a user
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);

        return "User deleted successfully";
    }

    // Delete a shelter
    @DeleteMapping("/shelters/{id}")
    public String deleteShelter(@PathVariable Long id) {

        shelterRepository.deleteById(id);

        return "Shelter deleted successfully";
    }

    // Delete a pet
    @DeleteMapping("/pets/{id}")
    public String deletePet(@PathVariable Long id) {

        petRepository.deleteById(id);

        return "Pet deleted successfully";
    }

    // Delete an adoption request
    @DeleteMapping("/adoption-requests/{id}")
    public String deleteAdoptionRequest(@PathVariable Long id) {

        adoptionRequestRepository.deleteById(id);

        return "Adoption request deleted successfully";
    }
}