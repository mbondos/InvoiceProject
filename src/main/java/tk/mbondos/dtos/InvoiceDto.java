package tk.mbondos.dtos;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mbondos.enums.PaymentType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class InvoiceDto {
    private Long id;
    @NotEmpty
    @Pattern(regexp = "([0-9][1-9]\\/(0[1-9]|1[0-2])\\/[1-9][0-9][0-9][0-9])")
    private String invoiceNumber;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate serviceDate;
    private CustomerDto customer;
    private OrganizationDto organization;
    private PaymentType paymentType;
    private List<InvoiceLinesDto> invoiceLines;

    private BigDecimal totalNetto;

    private BigDecimal totalTax;

    private BigDecimal totalBrutto;


    public InvoiceDto() {
    }

    public InvoiceDto(Long id, String invoiceNumber, LocalDate issueDate, LocalDate serviceDate, CustomerDto customer, OrganizationDto organization, PaymentType paymentType, List<InvoiceLinesDto> invoiceLines, BigDecimal totalNetto, BigDecimal totalTax, BigDecimal totalBrutto) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.serviceDate = serviceDate;
        this.customer = customer;
        this.organization = organization;
        this.paymentType = paymentType;
        this.invoiceLines = invoiceLines;
        this.totalNetto = totalNetto;
        this.totalTax = totalTax;
        this.totalBrutto = totalBrutto;
    }

    public InvoiceDto(String invoiceNumber, LocalDate issueDate, LocalDate serviceDate, CustomerDto customer, OrganizationDto organization, PaymentType paymentType, List<InvoiceLinesDto> invoiceLines) {
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.serviceDate = serviceDate;
        this.customer = customer;
        this.organization = organization;
        this.paymentType = paymentType;
        this.invoiceLines = invoiceLines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public OrganizationDto getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDto organization) {
        this.organization = organization;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<InvoiceLinesDto> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLinesDto> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public BigDecimal getTotalNetto() {
        return totalNetto;
    }

    public void setTotalNetto(BigDecimal totalNetto) {
        this.totalNetto = totalNetto;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalBrutto() {
        return totalBrutto;
    }

    public void setTotalBrutto(BigDecimal totalBrutto) {
        this.totalBrutto = totalBrutto;
    }

    public void validate() {
        //TODO validate
    }
}



