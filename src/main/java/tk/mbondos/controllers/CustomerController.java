package tk.mbondos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mbondos.domain.Customer;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public void addCustomer(CustomerDto customerDto) {
        customerDto.validate();
        customerService.createCustomer(customerDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Customer> searchCustomers(@RequestParam("name") String name) {
        return customerService.getCustomersByName(name);
    }

   /* @RequestMapping(method = RequestMethod.GET, value = "/{customerId}")
    public Customer getCustomer(@PathVariable Long customerId) {
        return customerService.listCustomer(customerId);
    }*/


}
