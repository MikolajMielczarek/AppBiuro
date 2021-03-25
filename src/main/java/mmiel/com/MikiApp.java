package mmiel.com;

import lombok.extern.slf4j.Slf4j;

import mmiel.com.entity.Customer;
import mmiel.com.repositories.CustomerRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@Slf4j //lombok
//@AllArgsConstructor //lombok
@SpringBootApplication
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class}) //disable WhileLabel error page
public class MikiApp implements CommandLineRunner {
    private final CustomerRepository customerRepository;


    public MikiApp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    public static void main(String[] args) {
        SpringApplication.run(MikiApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Customer customer=new Customer();
        customer.setFirstName("Mikołaj");
        customer.setLastName("Mielczarek");
        customer.setEmail("mielczarek.electro@gmail.com");
        customer.setPhone("65862185");
        customerRepository.save(customer);



        List <Customer> customerList=customerRepository.findAll();
        System.out.println(customerList);


    }


}
