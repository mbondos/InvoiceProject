package tk.mbondos.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.Customer;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private ModelMapper modelMapper;


    public CustomerService() {
    }

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer;
        if (customerDto.getId() != null)
             customer = modelMapper.map(customerDto, Customer.class);
        else
            customer = new Customer(customerDto.getName(), customerDto.getAddress(), customerDto.getNip());

        Customer save = customerRepository.save(customer);
        return save;
    }

    @Transactional
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer findCustomerById(Long customerId) {
        return customerRepository.findOne(customerId);
    }

    @Transactional
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByNameIgnoreCaseContaining(name);
    }
}
