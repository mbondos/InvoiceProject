package tk.mbondos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tk.mbondos.domain.Invoice;

@RepositoryRestResource(collectionResourceRel = "invoice", path = "invoices")
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
