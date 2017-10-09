package tk.mbondos.annotation;


import tk.mbondos.dtos.CustomerDto;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE} )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=ExistingOrNewCustomerValidator.class)
public @interface ExistingOrNewCustomer {
    String message() default "{Chose or add new customer}";
    Class<?>[] groups() default {};
    Class<? extends CustomerDto>[] payload() default {};

}
