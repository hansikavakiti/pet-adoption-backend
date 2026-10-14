package com.petadoption.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.petadoption.backend.entity.AdoptionRequest;
import com.petadoption.backend.entity.Pet;
import com.petadoption.backend.repository.AdoptionRequestRepository;
import com.petadoption.backend.repository.PetRepository;

@RestController
@RequestMapping("/adoption-requests")
@CrossOrigin(origins = "http://localhost:5173")
public class AdoptionRequestController {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final PetRepository petRepository;

    public AdoptionRequestController(
            AdoptionRequestRepository adoptionRequestRepository,
            PetRepository petRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.petRepository = petRepository;
    }

    // Get all adoption requests
    @GetMapping
    public List<AdoptionRequest> getAllRequests() {
        return adoptionRequestRepository.findAll();
    }

    // Get an adoption request by ID
    @GetMapping("/{id}")
    public AdoptionRequest getRequestById(@PathVariable Long id) {
        return adoptionRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Adoption request not found"
                ));
    }

    // Create an adoption request
    @PostMapping
    public AdoptionRequest createRequest(
            @RequestBody AdoptionRequest request) {
        return adoptionRequestRepository.save(request);
    }

    // Update adoption request status and pet status
    @PutMapping("/{id}/status")
    public AdoptionRequest updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        AdoptionRequest request =
                adoptionRequestRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Adoption request not found"
                        ));

        if (!status.equalsIgnoreCase("Approved")
                && !status.equalsIgnoreCase("Rejected")
                && !status.equalsIgnoreCase("Pending")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Status must be Approved, Rejected, or Pending"
            );
        }

        // Update the adoption request status
        request.setStatus(status);

        // If approved, update the associated pet
        if (status.equalsIgnoreCase("Approved")) {

            Long petId = request.getPetId();

            if (petId == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Pet ID is missing from the adoption request"
                );
            }

            Pet pet = petRepository.findById(petId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Pet not found"
                    ));

            pet.setStatus("Adopted");
            petRepository.save(pet);
        }

        return adoptionRequestRepository.save(request);
    }

    // Delete an adoption request
    @DeleteMapping("/{id}")
    public String deleteRequest(@PathVariable Long id) {

        AdoptionRequest request =
                adoptionRequestRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Adoption request not found"
                        ));

        adoptionRequestRepository.delete(request);

        return "Adoption request deleted successfully";
    }
}
