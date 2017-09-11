package tk.mbondos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tk.mbondos.domain.InvoiceLines;

@RepositoryRestResource
public interface InvoiceLinesRepository extends JpaRepository<InvoiceLines, Long> {
}
