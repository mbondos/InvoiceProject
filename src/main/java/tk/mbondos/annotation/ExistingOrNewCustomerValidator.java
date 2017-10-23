package tk.mbondos.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mbondos.domain.Customer;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.services.CustomerService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingOrNewCustomerValidator implements ConstraintValidator<ExistingOrNewCustomer, CustomerDto> {

    @Autowired
    private CustomerService customerService;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public void initialize(ExistingOrNewCustomer constraint) {
    }

    public boolean isValid(CustomerDto customer, ConstraintValidatorContext context) {
        if (customer.getId() != null) {
           Customer existingCustomer =  customerService.findCustomerById(customer.getId());
            if (!existingCustomer.getName().isEmpty() && existingCustomer.getAddress() != null && !existingCustomer.getNip().isEmpty()){
                log.info("Using existing customer");
                return true;
            }
        } else if (!customer.getName().isEmpty() && customer.getAddress() != null && !customer.getNip().isEmpty()) {
            log.info("Creating new customer");
            return true;
        }
        log.info("Failed customer validation");

        return false;
    }
}
