// Main class of the Spring Boot application
package pet.store;

// Spring Boot imports for application configuration and startup
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication is a composite annotation that combines three annotations:
// - @Configuration: Indicates that this class is a configuration class
// - @EnableAutoConfiguration: Enables Spring Boot's automatic configuration
// - @ComponentScan: Enables component scanning in the current package and subpackages
@SpringBootApplication
public class PetStoreApplication {

    // Main method that starts the Spring Boot application
    // args: Command line arguments that can be passed to the application
    public static void main(String[] args) {
        // SpringApplication.run() starts the Spring Boot application
        // - PetStoreApplication.class: The main application class
        // - args: The command line arguments
        SpringApplication.run(PetStoreApplication.class, args);
    }
}