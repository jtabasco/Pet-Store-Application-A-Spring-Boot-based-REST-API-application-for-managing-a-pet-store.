package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.store.entity.Customer;

/**
 * Data Access Object interface for Customer entities.
 * This interface extends JpaRepository to provide basic CRUD operations for
 * Customer entities.
 * Spring Data JPA will automatically implement this interface at runtime.
 */
public interface CustomerDao extends JpaRepository<Customer, Long> {
    // No additional methods needed as JpaRepository provides all basic CRUD
    // operations
}