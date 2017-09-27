package tk.mbondos.factories;

import org.springframework.stereotype.Component;
import tk.mbondos.domain.InvoiceLines;
import tk.mbondos.dtos.InvoiceLinesDto;

@Component
public class InvoiceLinesFactory {
    public InvoiceLinesFactory() {
    }

    public InvoiceLines create(InvoiceLinesDto invoiceLinesDto) {
        return new InvoiceLines(
                invoiceLinesDto.getQuantity(),
                invoiceLinesDto.getProduct(),
                invoiceLinesDto.getValueNetto(),
                invoiceLinesDto.getValueBrutto(),
                invoiceLinesDto.getValueTax());

    }
}
