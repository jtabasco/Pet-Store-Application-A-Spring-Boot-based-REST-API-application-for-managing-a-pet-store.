package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import pet.store.entity.PetStore;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for PetStore entities.
 * This class is used to transfer pet store data between the client and the
 * server.
 * It contains all the necessary fields to represent a pet store.
 */
@Data
@NoArgsConstructor
public class PetStoreData {

    private Long petStoreId; // Unique identifier for the pet store.
    private String petStoreName; // Name of the pet store.
    private String petStoreAddress; // Street address of the pet store.
    private String petStoreCity; // City where the pet store is located.
    private String petStoreState; // State where the pet store is located.
    private String petStoreZip; // ZIP code of the pet store.
    private String petStorePhone; // Contact phone number for the pet store.
    private Set<PetStoreCustomer> customers = new HashSet<>(); // Set of customers associated with this pet store.
    private Set<PetStoreEmployee> employees = new HashSet<>(); // Set of employees working at this pet store.

    /**
     * Constructor that creates a PetStoreData object from a PetStore entity.
     * 
     * @param petStore The PetStore entity to convert
     */
    public PetStoreData(PetStore petStore) {
        this.petStoreId = petStore.getPetStoreId();
        this.petStoreName = petStore.getPetStoreName();
        this.petStoreAddress = petStore.getPetStoreAddress();
        this.petStoreCity = petStore.getPetStoreCity();
        this.petStoreState = petStore.getPetStoreState();
        this.petStoreZip = petStore.getPetStoreZip();
        this.petStorePhone = petStore.getPetStorePhone();

        // Convert Customer entities to PetStoreCustomer objects
        for (Customer customer : petStore.getCustomers()) {
            this.customers.add(new PetStoreCustomer(customer));
        }

        // Convert Employee entities to PetStoreEmployee objects
        for (Employee employee : petStore.getEmployees()) {
            this.employees.add(new PetStoreEmployee(employee));
        }
    }

    /**
     * Data Transfer Object (DTO) for Customer entities.
     * This class is used to transfer customer data between the client and the
     * server.
     * It contains all the necessary fields to represent a customer.
     */
    @Data // Annotation to generate getters, setters, and toString method.
    @NoArgsConstructor // Annotation to generate a no-argument constructor.
    public static class PetStoreCustomer {
        private Long customerId;
        private String customerFirstName;
        private String customerLastName;
        private String customerEmail;

        /**
         * Constructor that creates a PetStoreCustomer object from a Customer entity.
         * 
         * @param customer The Customer entity to convert
         */
        public PetStoreCustomer(Customer customer) {
            this.customerId = customer.getCustomerId();
            this.customerFirstName = customer.getCustomerFirstName();
            this.customerLastName = customer.getCustomerLastName();
            this.customerEmail = customer.getCustomerEmail();
        }
    }

    /**
     * Data Transfer Object (DTO) for Employee entities.
     * This class is used to transfer employee data between the client and the
     * server.
     * It contains all the necessary fields to represent an employee.
     */
    @Data // Annotation to generate getters, setters, and toString method.
    @NoArgsConstructor // Annotation to generate a no-argument constructor.
    public static class PetStoreEmployee {
        private Long employeeId;
        private String employeeFirstName;
        private String employeeLastName;
        private String employeePhone;
        private String employeeJobTitle;

        /**
         * Constructor that creates a PetStoreEmployee object from an Employee entity.
         * 
         * @param employee The Employee entity to convert
         */
        public PetStoreEmployee(Employee employee) {
            this.employeeId = employee.getEmployeeId();
            this.employeeFirstName = employee.getEmployeeFirstName();
            this.employeeLastName = employee.getEmployeeLastName();
            this.employeePhone = employee.getEmployeePhone();
            this.employeeJobTitle = employee.getEmployeeJobTitle();
        }
    }
}
