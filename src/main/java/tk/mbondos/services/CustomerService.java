package tk.mbondos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.Customer;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.factories.CustomerFactory;
import tk.mbondos.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerFactory customerFactory;


    public CustomerService(CustomerRepository customerRepository, CustomerFactory customerFactory) {
        this.customerRepository = customerRepository;
        this.customerFactory = customerFactory;
    }

    @Transactional
    public void createCustomer(CustomerDto customerDto) {
        Customer customer = customerFactory.create(customerDto);
        customerRepository.save(customer);

    }

    @Transactional
    public List<Customer> listAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer listCustomer(Long customerId) {
        return customerRepository.findOne(customerId);
    }

    @Transactional
    public List<Customer> searchByName(String name) {
        return customerRepository.findByNameIgnoreCaseContaining(name);
    }
}
