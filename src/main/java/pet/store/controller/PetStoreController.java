package pet.store.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.controller.model.PetStoreCustomer;
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

    @PostMapping("/{petStoreId}/employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreEmployee addEmployeeToPetStore(
            @PathVariable Long petStoreId,
            @RequestBody PetStoreEmployee petStoreEmployee) {
        log.info("Adding employee {} to pet store with ID={}", petStoreEmployee, petStoreId);
        return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
    }

    @PostMapping("/{petStoreId}/customer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreCustomer addCustomerToPetStore(
            @PathVariable Long petStoreId,
            @RequestBody PetStoreCustomer petStoreCustomer) {
        log.info("Adding customer {} to pet store with ID={}", petStoreCustomer, petStoreId);
        return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
    }

    @GetMapping
    public List<PetStoreData> retrieveAllPetStores() {
        log.info("Retrieving all pet stores");
        return petStoreService.retrieveAllPetStores();
    }

    @GetMapping("/{petStoreId}")
    public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
        log.info("Retrieving pet store with ID={}", petStoreId);
        return petStoreService.retrievePetStoreById(petStoreId);
    }

    @DeleteMapping("/{petStoreId}")
    public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
        log.info("Deleting pet store with ID={}", petStoreId);
        petStoreService.deletePetStoreById(petStoreId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pet store with ID=" + petStoreId + " was deleted successfully");
        return response;
    }
}