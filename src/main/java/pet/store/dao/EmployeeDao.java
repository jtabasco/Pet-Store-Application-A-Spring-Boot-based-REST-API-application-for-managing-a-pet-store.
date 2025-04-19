package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.store.entity.Employee;

/**
 * Data Access Object interface for Employee entities.
 * This interface extends JpaRepository to provide basic CRUD operations for
 * Employee entities.
 * Spring Data JPA will automatically implement this interface at runtime.
 */
public interface EmployeeDao extends JpaRepository<Employee, Long> {
    // No additional methods needed as JpaRepository provides all basic CRUD
    // operations
}