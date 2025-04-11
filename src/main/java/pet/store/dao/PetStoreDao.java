package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.store.entity.PetStore;

/**
 * Data Access Object interface for PetStore entities.
 * This interface extends JpaRepository to provide basic CRUD operations for
 * PetStore entities.
 * Spring Data JPA will automatically implement this interface at runtime.
 */
public interface PetStoreDao extends JpaRepository<PetStore, Long> {
    // No additional methods needed as JpaRepository provides all basic CRUD
    // operations
}