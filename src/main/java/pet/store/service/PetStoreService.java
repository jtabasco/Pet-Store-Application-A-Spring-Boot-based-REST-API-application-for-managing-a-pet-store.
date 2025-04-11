package pet.store.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

/**
 * Service class for handling pet store business logic.
 * This class provides methods for saving and retrieving pet store data.
 */
@Service
public class PetStoreService {

    /**
     * Data Access Object for pet store entities.
     * Autowired by Spring to inject the implementation.
     */
    @Autowired
    private PetStoreDao petStoreDao;

    /**
     * Saves a pet store to the database.
     * If the pet store already exists, it will be updated.
     * If it doesn't exist, a new pet store will be created.
     * 
     * @param petStoreData The pet store data to save
     * @return The saved pet store data
     */
    public PetStoreData savePetStore(PetStoreData petStoreData) {
        PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
        copyPetStoreFields(petStore, petStoreData);
        PetStore savedPetStore = petStoreDao.save(petStore);
        return new PetStoreData(savedPetStore);
    }

    /**
     * Finds an existing pet store by ID or creates a new one if it doesn't exist.
     * 
     * @param petStoreId The ID of the pet store to find
     * @return The found or newly created pet store
     */
    private PetStore findOrCreatePetStore(Long petStoreId) {
        if (petStoreId == null) {
            return new PetStore();
        }
        return findPetStoreById(petStoreId);
    }

    /**
     * Finds a pet store by ID.
     * Throws a NoSuchElementException if the pet store is not found.
     * 
     * @param petStoreId The ID of the pet store to find
     * @return The found pet store
     * @throws NoSuchElementException if the pet store is not found
     */
    private PetStore findPetStoreById(Long petStoreId) {
        return petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Pet store with ID=%d was not found", petStoreId)));
    }

    /**
     * Copies fields from a PetStoreData object to a PetStore entity.
     * 
     * @param petStore     The PetStore entity to update
     * @param petStoreData The PetStoreData object containing the new values
     */
    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
    }
}
