// Class that represents an employee in a pet store
package pet.store.entity;

// JPA imports for persistence annotations
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// Lombok imports to reduce boilerplate code
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// @Entity indicates that this class is a JPA entity
// and will be mapped to a database table
@Entity

// @Data from Lombok automatically generates:
// - Getters and setters
// - toString()
// - equals() and hashCode()
// - No-args constructor
@Data
public class Employee {
    // @Id marks this field as the primary key of the table
    @Id

    // @GeneratedValue indicates that the ID will be automatically generated
    // GenerationType.IDENTITY uses MySQL's auto-increment column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    // Basic employee information fields
    private String employeeFirstName; // Employee's first name
    private String employeeLastName; // Employee's last name
    private String employeePhone; // Employee's contact phone
    private String employeeJobTitle; // Employee's job position

    // Many-to-one relationship with PetStore
    // Many employees can work at one store
    // cascade = ALL means all operations (create, update, delete) are cascaded
    @ManyToOne(cascade = CascadeType.ALL)

    // @JoinColumn specifies the foreign key column in the employee table
    @JoinColumn(name = "pet_store_id")

    // @EqualsAndHashCode.Exclude prevents the petStore field from being included
    // in equals() and hashCode() methods to avoid infinite recursion
    @EqualsAndHashCode.Exclude

    // @ToString.Exclude prevents the petStore field from being included
    // in toString() method to avoid infinite recursion
    @ToString.Exclude

    // Reference to the pet store where this employee works
    private PetStore petStore;
}
