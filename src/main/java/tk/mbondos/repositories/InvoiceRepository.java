package tk.mbondos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.Invoice;

import java.util.List;
import java.util.Map;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findTopByOrderByIdDesc();

    List<Invoice> findAllByOrderByServiceDate();
}
