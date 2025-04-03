// Class that represents a customer in the pet store
package pet.store.entity;

// JPA imports for persistence annotations
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

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
public class Customer {
    // @Id marks this field as the primary key of the table
    @Id

    // @GeneratedValue indicates that the ID will be automatically generated
    // GenerationType.IDENTITY uses MySQL's auto-increment column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    // Basic customer fields
    private String customerFirstName; // Customer's first name
    private String customerLastName; // Customer's last name
    private String customerEmail; // Customer's email address

    // Many-to-many relationship with PetStore
    // A customer can visit many stores and a store can have many customers
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)

    // @EqualsAndHashCode.Exclude prevents the petStores field from being included
    // in equals() and hashCode() methods to avoid infinite recursion
    @EqualsAndHashCode.Exclude

    // @ToString.Exclude prevents the petStores field from being included
    // in toString() method to avoid infinite recursion
    @ToString.Exclude

    // Collection of pet stores that the customer visits
    // Initialized as HashSet to avoid NullPointerException
    private Set<PetStore> petStores = new HashSet<>();
}
