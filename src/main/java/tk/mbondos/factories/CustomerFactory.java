package tk.mbondos.factories;

import org.springframework.stereotype.Component;
import tk.mbondos.domain.Customer;
import tk.mbondos.dtos.CustomerDto;

@Component
public class CustomerFactory {

    public CustomerFactory() {
    }

    public Customer create(CustomerDto customerDto) {
        return new Customer(customerDto.getName(), customerDto.getNip());
    }
}
