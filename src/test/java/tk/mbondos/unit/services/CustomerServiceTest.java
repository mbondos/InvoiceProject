package tk.mbondos.unit.services;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.embeddable.Address;
import tk.mbondos.repositories.CustomerRepository;
import tk.mbondos.services.CustomerService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @TestConfiguration
    static class CustomerServiceTestContextConfiguration {
        @Bean
        public CustomerService customerService() {
            return new CustomerService();
        }
    }

    @Before
    public void setUp() {
        Customer customer = new Customer("John Smith", new Address("Street", "City", "zip"),"nip" );
        Mockito.when(customerRepository.findByNameIgnoreCaseContaining(customer.getName())).thenReturn(Arrays.asList(customer));
    }

    @Test
    public void whenValidName_thenCustomerShouldBeFound() {
        String name = "John Smith";
        List<Customer> found = customerService.getCustomersByName(name);

        assertThat(found.get(0).getName()).isEqualToIgnoringCase(name);
    }


}
