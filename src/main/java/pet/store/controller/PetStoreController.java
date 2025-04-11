package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

/**
 * REST Controller for managing pet store operations.
 * This controller provides endpoints for creating and updating pet stores.
 */
@RestController // Annotation to mark the class as a REST controller.
@RequestMapping("/pet_store") // Annotation to map the controller to a specific URL path.
@Slf4j // Annotation to enable logging for the class.
public class PetStoreController {

    /**
     * Service for handling pet store business logic.
     * Autowired by Spring to inject the implementation.
     */
    @Autowired // Annotation to inject the PetStoreService bean into the controller.
    private PetStoreService petStoreService;

    /**
     * Creates a new pet store.
     * 
     * @param petStoreData The pet store data to create
     * @return The created pet store data
     */
    @PostMapping // Annotation to map the createPetStore method to a POST request.
    @ResponseStatus(code = HttpStatus.CREATED) // Annotation to set the HTTP status code to 201 Created.
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Creating pet store {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }

    /**
     * Updates an existing pet store.
     * 
     * @param petStoreId   The ID of the pet store to update
     * @param petStoreData The updated pet store data
     * @return The updated pet store data
     */
    @PutMapping("/{petStoreId}") // Annotation to map the updatePetStore method to a PUT request.
    @ResponseStatus(code = HttpStatus.OK) // Annotation to set the HTTP status
    // code to 200 OK.
    public PetStoreData updatePetStore(@PathVariable Long petStoreId,
            @RequestBody PetStoreData petStoreData) {
        petStoreData.setPetStoreId(petStoreId); // Sets the pet store ID to the ID of the pet store to update.
       
        log.info("Updating pet store with ID: {}", petStoreId);

        return petStoreService.savePetStore(petStoreData);
    }
}