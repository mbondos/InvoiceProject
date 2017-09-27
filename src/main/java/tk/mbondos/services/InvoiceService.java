package tk.mbondos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.*;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.dtos.InvoiceDto;
import tk.mbondos.dtos.InvoiceLinesDto;
import tk.mbondos.dtos.OrganizationDto;
import tk.mbondos.factories.CustomerFactory;
import tk.mbondos.factories.InvoiceFactory;
import tk.mbondos.factories.InvoiceLinesFactory;
import tk.mbondos.factories.OrganizationFactory;
import tk.mbondos.repositories.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceFactory invoiceFactory;
    private CustomerFactory customerFactory;
    private CustomerRepository customerRepository;
    private InvoiceLinesFactory invoiceLinesFactory;
    private InvoiceLinesRepository invoiceLinesRepository;
    private ProductRepository productRepository;
    private OrganizationRepository organizationRepository;
    private OrganizationService organizationService;
    private OrganizationFactory organizationFactory;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceFactory invoiceFactory, CustomerFactory customerFactory, CustomerRepository customerRepository, InvoiceLinesFactory invoiceLinesFactory, InvoiceLinesRepository invoiceLinesRepository, ProductRepository productRepository, OrganizationRepository organizationRepository, OrganizationService organizationService, OrganizationFactory organizationFactory) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceFactory = invoiceFactory;
        this.customerFactory = customerFactory;
        this.customerRepository = customerRepository;
        this.invoiceLinesFactory = invoiceLinesFactory;
        this.invoiceLinesRepository = invoiceLinesRepository;
        this.productRepository = productRepository;
        this.organizationRepository = organizationRepository;
        this.organizationService = organizationService;
        this.organizationFactory = organizationFactory;
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



    @Transactional
    public void createInvoice(InvoiceDto invoiceDto, CustomerDto customerDto, OrganizationDto organizationDto, List<InvoiceLinesDto> invoiceLinesDtos) {
        Invoice invoice = invoiceFactory.create(invoiceDto);
        Customer customer;
        Organization organization;
        List<InvoiceLines> invoiceLines = new ArrayList<>();

        for (InvoiceLinesDto linesDto : invoiceLinesDtos) {
            if (linesDto != null
                    && linesDto.getProduct() != null
                    && !linesDto.getProduct().getName().isEmpty() ) {

                invoiceLines.add(invoiceLinesFactory.create(linesDto));
                Product product = linesDto.getProduct();
                productRepository.save(product);
            }
        }
        if (invoiceLines.size() != 0)
            invoiceLinesRepository.save(invoiceLines);

        if (customerDto.getId() != null) {
            customer = customerRepository.findOne(customerDto.getId());
        } else {
            customer = customerFactory.create(customerDto);
            customerRepository.save(customer);
        }
        if (organizationDto.getId() != null) {
            organization = organizationService.findById(organizationDto.getId());
        } else {
            organization = organizationFactory.create(organizationDto);
            organizationRepository.save(organization);
        }

        invoice.setInvoiceLines(invoiceLines);
        invoice.setCustomer(customer);
        invoice.setOrganization(organization); //TODO bind invoice with organization
        invoiceRepository.save(invoice);
    }


}
