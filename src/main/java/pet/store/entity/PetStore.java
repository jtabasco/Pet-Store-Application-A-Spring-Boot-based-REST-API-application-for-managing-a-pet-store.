// Class that represents a pet store in the system
package pet.store.entity;

// JPA imports for persistence annotations
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

// Lombok imports to reduce boilerplate code
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// Java imports for collections
import java.util.Set;
import java.util.HashSet;

// @Entity indicates that this class is a JPA entity
// and will be mapped to a database table
@Entity

// @Data from Lombok automatically generates:
// - Getters and setters
// - toString()
// - equals() and hashCode()
// - No-args constructor
@Data
public class PetStore {
        // @Id marks this field as the primary key of the table
        @Id

        // @GeneratedValue indicates that the ID will be automatically generated
        // GenerationType.IDENTITY uses MySQL's auto-increment column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long petStoreId;

        // Basic store information fields
        private String petStoreName; // Name of the pet store
        private String petStoreAddress; // Street address of the store
        private String petStoreCity; // City where the store is located
        private String petStoreState; // State where the store is located
        private String petStoreZip; // ZIP code of the store
        private String petStorePhone; // Contact phone number

        // Many-to-many relationship with Customer
        // A store can have many customers and a customer can visit many stores
        // cascade = PERSIST means new customers will be persisted when added to the
        // store
        @ManyToMany(cascade = CascadeType.PERSIST)

        // @JoinTable creates the junction table for the many-to-many relationship
        @JoinTable(name = "pet_store_customer", joinColumns = @JoinColumn(name = "pet_store_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))

        // @EqualsAndHashCode.Exclude prevents the customers field from being included
        // in equals() and hashCode() methods to avoid infinite recursion
        @EqualsAndHashCode.Exclude

        // @ToString.Exclude prevents the customers field from being included
        // in toString() method to avoid infinite recursion
        @ToString.Exclude

        // Collection of customers that visit this store
        // Initialized as HashSet to avoid NullPointerException
        private Set<Customer> customers = new HashSet<>();

        // One-to-many relationship with Employee
        // One store can have many employees
        // cascade = ALL means all operations (create, update, delete) are cascaded
        // orphanRemoval = true means if an employee is removed from the set,
        // it will be deleted from the database
        @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)

        // @EqualsAndHashCode.Exclude prevents the employees field from being included
        // in equals() and hashCode() methods to avoid infinite recursion
        @EqualsAndHashCode.Exclude

        // @ToString.Exclude prevents the employees field from being included
        // in toString() method to avoid infinite recursion
        @ToString.Exclude

        // Collection of employees that work at this store
        // Initialized as HashSet to avoid NullPointerException
        private Set<Employee> employees = new HashSet<>();
}
