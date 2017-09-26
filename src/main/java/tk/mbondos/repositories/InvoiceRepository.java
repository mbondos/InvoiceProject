package tk.mbondos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.Invoice;

import java.util.Map;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
