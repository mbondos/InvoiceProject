package tk.mbondos.services;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.*;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.dtos.InvoiceDto;
import tk.mbondos.dtos.InvoiceLinesDto;

import tk.mbondos.repositories.*;

import java.time.LocalDate;

import java.util.List;


@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceLinesService invoiceLinesService;
    private OrganizationService organizationService;
    private CustomerService customerService;

    private ModelMapper modelMapper;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceLinesService invoiceLinesService, OrganizationService organizationService, CustomerService customerService, ModelMapper modelMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceLinesService = invoiceLinesService;
        this.organizationService = organizationService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Invoice createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
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
        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
        invoiceRepository.save(invoice);
        Customer customer;
        Organization organization;
        List<InvoiceLines> invoiceLines = invoiceLinesService.create(invoiceLinesDtos);

        //Create new or find existing Customer
        if (customerDto.getId() != null) {
            customer = customerService.findCustomerById(customerDto.getId());
        } else {
            customer = customerService.createCustomer(customerDto);
        }

        organization = organizationService.findById(1L); //TODO default organization functionality


        invoice.setInvoiceLines(invoiceLines);
        invoice.setCustomer(customer);
        invoice.setOrganization(organization);
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void updateInvoice(InvoiceDto invoiceDto, List<InvoiceLinesDto> invoiceLinesDtos) {
        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
        if (invoiceDto.getCustomer().getName().isEmpty()){
            invoice.setCustomer(customerService.findCustomerById(invoiceDto.getCustomer().getId()));
            log.info("Customer from search: {}, {}", invoice.getCustomer().getName(), invoice.getCustomer().getId());
        }else {
            invoice.setCustomer(customerService.createCustomer(invoiceDto.getCustomer()));
            log.info("Edited or new customer");
        }
        invoice.setInvoiceLines(invoiceLinesService.create(invoiceLinesDtos));
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoice(Long invoiceId) {
        invoiceRepository.delete(invoiceId);
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
            invoiceNumber = String.format("%02d/%02d/%d", ++last, currentDate.getMonthValue(), currentDate.getYear());
        } else {
            invoiceNumber = String.format("%02d/%02d/%d", 1, currentDate.getMonthValue(), currentDate.getYear());
            log.info("Last invoice not current month");
        }

        log.info("Next Invoice number: {}", invoiceNumber);

        return invoiceNumber;
    }


}
