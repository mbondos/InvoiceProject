package tk.mbondos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.InvoiceLines;
import tk.mbondos.dtos.InvoiceLinesDto;
import tk.mbondos.factories.InvoiceLinesFactory;
import tk.mbondos.repositories.InvoiceLinesRepository;
import tk.mbondos.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceLinesService{

    private InvoiceLinesFactory invoiceLinesFactory;
    private ProductRepository productRepository;
    private InvoiceLinesRepository invoiceLinesRepository;

    public InvoiceLinesService(InvoiceLinesFactory invoiceLinesFactory, ProductRepository productRepository, InvoiceLinesRepository invoiceLinesRepository) {
        this.invoiceLinesFactory = invoiceLinesFactory;
        this.productRepository = productRepository;
        this.invoiceLinesRepository = invoiceLinesRepository;
    }

    @Transactional
    public List<InvoiceLines> create(List<InvoiceLinesDto> invoiceLinesDtos) {
        List<InvoiceLines> invoiceLines = new ArrayList<>();

        for (InvoiceLinesDto lineDto : invoiceLinesDtos) {
            if (lineDto != null
                    && lineDto.getProduct() != null
                    && !lineDto.getProduct().getName().isEmpty() ) {

                invoiceLines.add(invoiceLinesFactory.create(lineDto));
                productRepository.save(lineDto.getProduct());
            }
        }

        if (invoiceLines.size() != 0)
            invoiceLinesRepository.save(invoiceLines);

        return invoiceLines;

    }
}
