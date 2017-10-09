package tk.mbondos.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mbondos.domain.Customer;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.services.CustomerService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingOrNewCustomerValidator implements ConstraintValidator<ExistingOrNewCustomer, CustomerDto> {

    @Autowired
    private CustomerService customerService;

    public void initialize(ExistingOrNewCustomer constraint) {
    }

    public boolean isValid(CustomerDto customer, ConstraintValidatorContext context) {
        if (customer.getId() != null) {
           Customer asd =  customerService.findCustomerById(customer.getId());
            if (asd.getName() != null || asd.getAddress() != null || asd.getNip() != null){
                return true;
            }
        } else if (customer.getName() != null || customer.getAddress() != null || customer.getNip() != null) {
            return true;
        }

        return false;
    }
}
