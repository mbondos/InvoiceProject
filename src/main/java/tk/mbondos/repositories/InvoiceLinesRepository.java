package tk.mbondos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.mbondos.domain.InvoiceLines;

@Repository
public interface InvoiceLinesRepository extends JpaRepository<InvoiceLines, Long> {
}
