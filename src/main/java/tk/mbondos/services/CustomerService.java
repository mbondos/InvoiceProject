package tk.mbondos.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.Customer;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;


    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
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
