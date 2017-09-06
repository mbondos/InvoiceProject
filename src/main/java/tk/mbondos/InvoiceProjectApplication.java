package tk.mbondos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mbondos.domain.Customer;
import tk.mbondos.repositories.CustomerRepository;

import java.util.Arrays;

@SpringBootApplication
public class InvoiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(CustomerRepository customerRepository) {
		return (evt) -> Arrays.asList(
				"Jones,Smith,Williams".split(","))
				.forEach(
						a -> {
							customerRepository.save(new Customer("asd", "123"));
						});

	}
}
