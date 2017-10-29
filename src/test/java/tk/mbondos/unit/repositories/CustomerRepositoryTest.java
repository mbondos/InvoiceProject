package tk.mbondos.unit.repositories;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.embeddable.Address;
import tk.mbondos.repositories.CustomerRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByName_thenReturnCustomer() {
        //given
        Customer customer = new Customer("John Smith", new Address("Street", "City", "zip"),"nip" );
        entityManager.persist(customer);
        entityManager.flush();

        //when
        List<Customer> found = customerRepository.findByNameIgnoreCaseContaining(customer.getName());

        //then
        assertThat(found.get(0).getName()).isEqualToIgnoringCase(customer.getName());
    }

}
