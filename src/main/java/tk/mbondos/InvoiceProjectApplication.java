package tk.mbondos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mbondos.domain.*;
import tk.mbondos.domain.embeddable.Address;
import tk.mbondos.enums.PaymentType;
import tk.mbondos.repositories.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class InvoiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(CustomerRepository customerRepository, InvoiceRepository invoiceRepository,
								  OrganizationRepository organizationRepository, InvoiceLinesRepository invoiceLinesRepository,
								  ProductRepository productRepository) {
		return (evt) -> Arrays.asList(
				"Jones,Smith,Williams".split(","))
				.forEach(
						a -> {
							Customer customer = new Customer(a, new Address("Street", "City", "zipcode"), "123");
							customerRepository.save(customer);
							List<InvoiceLines> invoiceLines = new ArrayList<>();
							Product product = new Product(a, new BigDecimal(100), new BigDecimal(123), 23);
							productRepository.save(product);
							InvoiceLines invoiceLines1 = new InvoiceLines(1, product);
							invoiceLines.add(invoiceLines1);
							invoiceLinesRepository.save(invoiceLines);
							Organization organization = new Organization("ASD", new Address("Street", "City", "zipcode"),
									"nip", "phone","email", "website", "12312313");
							organizationRepository.save(organization);
							invoiceRepository.save(new Invoice("01/09/2017", LocalDate.of(2017,5,23), LocalDate.of(2017,5,23), customer,
									organization, PaymentType.CASH, invoiceLines));
						});

	}
}
