package tk.mbondos.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceFactory invoiceFactory;
    private InvoiceLinesService invoiceLinesService;

    private OrganizationService organizationService;

    private CustomerService customerService;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceFactory invoiceFactory, InvoiceLinesService invoiceLinesService, OrganizationService organizationService, CustomerService customerService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceFactory = invoiceFactory;
        this.invoiceLinesService = invoiceLinesService;
        this.organizationService = organizationService;
        this.customerService = customerService;
    }

    @Transactional
    public Invoice createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceFactory.create(invoiceDto);
        invoiceRepository.save(invoice);
        return invoice;
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
    public void createInvoice(InvoiceDto invoiceDto, CustomerDto customerDto, List<InvoiceLinesDto> invoiceLinesDtos) {
        Invoice invoice = invoiceFactory.create(invoiceDto);
        Customer customer;
        Organization organization;
        List<InvoiceLines> invoiceLines = invoiceLinesService.create(invoiceLinesDtos);

        //Create new or find existing Customer
        if (customerDto.getId() != null) {
            customer = customerService.findCustomerById(customerDto.getId());
        } else {
            customer = customerService.createCustomer(customerDto);
        }

        organization = organizationService.findById((long) 1); //TODO default organization functionality


        invoice.setInvoiceLines(invoiceLines);
        invoice.setCustomer(customer);
        invoice.setOrganization(organization);
        invoiceRepository.save(invoice);
    }

    @Transactional
    public String getNextInvoiceNumber() {
        String invoiceNumber = "";

        LocalDate currentDate = LocalDate.now();
        Invoice maxId = invoiceRepository.findTopByOrderByIdDesc();
        LocalDate maxIdDate = maxId.getIssueDate();

        if (currentDate.getMonth().equals(maxIdDate.getMonth())
                && currentDate.getYear() == maxIdDate.getYear()) {
            int last = Integer.parseInt(maxId.getInvoiceNumber().substring(0,2));
            log.info("Last invoice number: {}", last);
            invoiceNumber = String.format("%02d/%02d/%d", ++last, currentDate.getMonthValue(), currentDate.getYear());


        } else {
            invoiceNumber = String.format("%02d/%02d/%d", 1, currentDate.getMonthValue(), currentDate.getYear());
            log.info("Last invoice not current month");
        }


        log.info("id: {}", maxId.getId());
        log.info("Invoice number: {}", invoiceNumber);

        return invoiceNumber;
    }


}
