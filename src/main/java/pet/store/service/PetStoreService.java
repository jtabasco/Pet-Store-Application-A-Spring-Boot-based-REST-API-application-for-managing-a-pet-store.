package pet.store.service;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.dao.PetStoreDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.CustomerDao;
import pet.store.entity.PetStore;
import pet.store.entity.Employee;
import pet.store.entity.Customer;

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

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private CustomerDao customerDao;

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

    @Transactional(readOnly = false)
    public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
        PetStore petStore = findPetStoreById(petStoreId);
        Employee employee = findOrCreateEmployee(petStoreEmployee.getEmployeeId(), petStoreId);

        copyEmployeeFields(employee, petStoreEmployee);
        employee.setPetStore(petStore);
        petStore.getEmployees().add(employee);

        Employee savedEmployee = employeeDao.save(employee);
        return new PetStoreEmployee(savedEmployee);
    }

    private Employee findEmployeeById(Long petStoreId, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Employee with ID=%d was not found", employeeId)));

        if (!employee.getPetStore().getPetStoreId().equals(petStoreId)) {
            throw new IllegalArgumentException(
                    String.format("Employee with ID=%d does not belong to pet store with ID=%d",
                            employeeId, petStoreId));
        }

        return employee;
    }

    private Employee findOrCreateEmployee(Long employeeId, Long petStoreId) {
        if (employeeId == null) {
            return new Employee();
        }
        return findEmployeeById(petStoreId, employeeId);
    }

    private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
        employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
        employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
        employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
    }

    @Transactional(readOnly = false)
    public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
        PetStore petStore = findPetStoreById(petStoreId);
        Customer customer = findOrCreateCustomer(petStoreCustomer.getCustomerId(), petStoreId);

        copyCustomerFields(customer, petStoreCustomer);
        customer.getPetStores().add(petStore);
        petStore.getCustomers().add(customer);

        Customer savedCustomer = customerDao.save(customer);
        return new PetStoreCustomer(savedCustomer);
    }

    private Customer findCustomerById(Long petStoreId, Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Customer with ID=%d was not found", customerId)));

        boolean found = customer.getPetStores().stream()
                .anyMatch(store -> store.getPetStoreId().equals(petStoreId));

        if (!found) {
            throw new IllegalArgumentException(
                    String.format("Customer with ID=%d does not belong to pet store with ID=%d",
                            customerId, petStoreId));
        }

        return customer;
    }

    private Customer findOrCreateCustomer(Long customerId, Long petStoreId) {
        if (customerId == null) {
            return new Customer();
        }
        return findCustomerById(petStoreId, customerId);
    }

    private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
        customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
        customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
        customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
    }

    @Transactional(readOnly = true)
    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        List<PetStoreData> result = new ArrayList<>();

        for (PetStore petStore : petStores) {
            PetStoreData petStoreData = new PetStoreData(petStore);
            petStoreData.getCustomers().clear();
            petStoreData.getEmployees().clear();
            result.add(petStoreData);
        }

        return result;
    }

    @Transactional(readOnly = true)
    public PetStoreData retrievePetStoreById(Long petStoreId) {
        PetStore petStore = findPetStoreById(petStoreId);
        return new PetStoreData(petStore);
    }

    @Transactional(readOnly = false)
    public void deletePetStoreById(Long petStoreId) {
        PetStore petStore = findPetStoreById(petStoreId);
        petStoreDao.delete(petStore);
    }
}
