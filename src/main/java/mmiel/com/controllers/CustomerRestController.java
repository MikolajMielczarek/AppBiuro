package mmiel.com.controllers;
import mmiel.com.ResourceNotFoundException;
import mmiel.com.entity.Customer;
import mmiel.com.repositories.CustomerRepository;
import mmiel.com.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customers")

public class CustomerRestController {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomerRestController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }


    @GetMapping("/{customerId}")
    public Customer findById(@PathVariable(name = "customerId") Long customerId) throws ResourceNotFoundException {
        return customerService.findById(customerId);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }


    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        customerService.deleteUser(customerId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntity<Customer>> updateCustomer(@PathVariable(value = "id") Long customerId,
                                                                   @RequestBody Customer customerDetails) throws ResourceNotFoundException {

        final ResponseEntity<Customer> updatedCustomer = customerService.updatedCustomer(customerId, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

}