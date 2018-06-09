package tk.mbondos.unit.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.Invoice;
import tk.mbondos.domain.Organization;
import tk.mbondos.domain.embeddable.Address;
import tk.mbondos.enums.PaymentType;
import tk.mbondos.repositories.InvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class InvoiceRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InvoiceRepository invoiceRepository;

    Invoice invoice;
    Invoice invoice1;

    @Before
    public void setUp() {

        invoice = new Invoice("01/01/2018", LocalDate.now(), LocalDate.now(),
                new Customer("John Smith",
                        new Address("Street", "City", "zip"),
                        "nip" ),
                new Organization("Organization",
                        new Address("Street", "City", "zip"),
                        "723-103-99-203", "phone", "mail", "website", "bank nubmer"),
                PaymentType.TRANSFER,
                null, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);

        invoice1 = new Invoice("02/01/2018", LocalDate.now().plusDays(1), LocalDate.now().plusDays(1),
                new Customer("John Smith",
                        new Address("Street", "City", "zip"),
                        "nip" ),
                new Organization("Organization",
                        new Address("Street", "City", "zip"),
                        "723-103-99-203", "phone", "mail", "website", "bank nubmer"),
                PaymentType.TRANSFER,
                null, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
        entityManager.persist(invoice);
        entityManager.persist(invoice1);
        entityManager.flush();
    }

    @Test
    public void whenFindTopById_thenReturnInvoice() {
        //when
        Invoice found = invoiceRepository.findTopByOrderByIdDesc();

        //then
        assertThat(found.getId()).isEqualTo(invoice1.getId());
    }

    @Test
    public void whenFindOrderByServiceDate_thenReturnInvoice() {
        //when
        List<Invoice> found = invoiceRepository.findAllByOrderByServiceDate();

        //then
        assertThat(found.get(0).getServiceDate()).isLessThanOrEqualTo(invoice.getServiceDate());
    }

}
