package tk.mbondos.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.InvoiceLines;
import tk.mbondos.dtos.InvoiceLinesDto;
import tk.mbondos.repositories.InvoiceLinesRepository;
import tk.mbondos.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceLinesService{
    private ProductRepository productRepository;
    private InvoiceLinesRepository invoiceLinesRepository;
    private ModelMapper modelMapper;

    public InvoiceLinesService(ProductRepository productRepository, InvoiceLinesRepository invoiceLinesRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.invoiceLinesRepository = invoiceLinesRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<InvoiceLines> create(List<InvoiceLinesDto> invoiceLinesDtos) {
        List<InvoiceLines> invoiceLines = new ArrayList<>();

        for (InvoiceLinesDto lineDto : invoiceLinesDtos) {
            if (lineDto != null
                    && lineDto.getProduct() != null
                    && !lineDto.getProduct().getName().isEmpty() ) {

                InvoiceLines lines = modelMapper.map(lineDto, InvoiceLines.class);
                invoiceLines.add(lines);
                productRepository.save(lines.getProduct());
            }
        }

        if (invoiceLines.size() != 0)
            invoiceLinesRepository.save(invoiceLines);

        return invoiceLines;

    }
}
