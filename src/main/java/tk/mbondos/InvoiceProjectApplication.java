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
				"John Jones,Jonny Wiertara,Rebeca Williams".split(","))
				.forEach(
						a -> {
							Customer customer = new Customer(a, new Address("ul. Lubartowska 54", "Lublin", "20-256"), "1526939481");
							customerRepository.save(customer);
							List<InvoiceLines> invoiceLines = new ArrayList<>();
							Product product = new Product(a, new BigDecimal(100), new BigDecimal(123), 23);
							productRepository.save(product);
							InvoiceLines invoiceLines1 = new InvoiceLines(1, product, new BigDecimal(100), new BigDecimal(123), new BigDecimal(23));
							InvoiceLines invoiceLines2 = new InvoiceLines(1, product, new BigDecimal(100), new BigDecimal(123), new BigDecimal(23));
							InvoiceLines invoiceLines3 = new InvoiceLines(1, product, new BigDecimal(100), new BigDecimal(123), new BigDecimal(23));
							invoiceLines.add(invoiceLines1);
							invoiceLines.add(invoiceLines2);
							invoiceLines.add(invoiceLines3);
							invoiceLinesRepository.save(invoiceLines);
							Organization organization = new Organization("Organization inc.", new Address("Bakers Street 54", "London", "20-255"),
									"4299302396", "666 666 666","tras-rubiez123@gmail.com", "website", "61 1090 1014 0000 0712 1981 2874");
							organizationRepository.save(organization);
							invoiceRepository.save(new Invoice("01/09/2017", LocalDate.of(2017,5,23), LocalDate.of(2017,5,23), customer,
									organization, PaymentType.CASH, invoiceLines, new BigDecimal(300), new BigDecimal(69), new BigDecimal(369)));
						});

	}


}
