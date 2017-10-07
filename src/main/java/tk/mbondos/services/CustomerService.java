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
    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = customerFactory.create(customerDto);
        Customer save = customerRepository.save(customer);
        return save;
    }

    @Transactional
    public List<Customer> listAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer findCustomerById(Long customerId) {
        return customerRepository.findOne(customerId);
    }

    @Transactional
    public List<Customer> searchByName(String name) {
        return customerRepository.findByNameIgnoreCaseContaining(name);
    }
}
