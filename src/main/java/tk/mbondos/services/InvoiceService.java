package tk.mbondos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.Invoice;
import tk.mbondos.dtos.InvoiceDto;
import tk.mbondos.factories.InvoiceFactory;
import tk.mbondos.repositories.InvoiceRepository;

import java.util.List;

@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceFactory invoiceFactory;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceFactory invoiceFactory) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceFactory = invoiceFactory;
    }

    @Transactional
    public void createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceFactory.create(invoiceDto);
        invoiceRepository.save(invoice);
    }

    @Transactional
    public List<Invoice> listAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Transactional
    public Invoice findById(Long id) {
        return invoiceRepository.findOne(id);
    }
}
