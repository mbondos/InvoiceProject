package tk.mbondos.factories;

import org.springframework.stereotype.Component;
import tk.mbondos.domain.Invoice;
import tk.mbondos.dtos.InvoiceDto;

@Component
public class InvoiceFactory {
    public InvoiceFactory() {
    }

    public Invoice create(InvoiceDto invoiceDto) {
        return new Invoice(
                invoiceDto.getInvoiceNumber(),
                invoiceDto.getIssueDate(),
                invoiceDto.getServiceDate(),
                invoiceDto.getCustomer(),
                invoiceDto.getOrganization(),
                invoiceDto.getPaymentType(),
                invoiceDto.getInvoiceLines());

    }
}
