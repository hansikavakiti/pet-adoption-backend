package com.petadoption.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petadoption.backend.entity.Pet;
import com.petadoption.backend.repository.PetRepository;

@RestController
@RequestMapping("/pets")
@CrossOrigin
public class PetController {

    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pet> getPetById(@PathVariable Long id) {
        return petRepository.findById(id);
    }

    @PostMapping
    public Pet addPet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {

        Pet existingPet = petRepository.findById(id).orElseThrow();

        existingPet.setName(pet.getName());
        existingPet.setAnimalType(pet.getAnimalType());
        existingPet.setBreed(pet.getBreed());
        existingPet.setAge(pet.getAge());
        existingPet.setGender(pet.getGender());
        existingPet.setDescription(pet.getDescription());
        existingPet.setLocation(pet.getLocation());
        existingPet.setImageUrl(pet.getImageUrl());
        existingPet.setStatus(pet.getStatus());
        existingPet.setShelterId(pet.getShelterId());

        return petRepository.save(existingPet);
    }

    @DeleteMapping("/{id}")
    public String deletePet(@PathVariable Long id) {
        petRepository.deleteById(id);
        return "Pet deleted successfully";
    }
}