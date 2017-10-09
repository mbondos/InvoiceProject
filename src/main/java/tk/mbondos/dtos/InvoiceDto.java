package tk.mbondos.dtos;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.InvoiceLines;
import tk.mbondos.domain.Organization;
import tk.mbondos.enums.PaymentType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class InvoiceDto {

    @NotEmpty
    private String invoiceNumber;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate serviceDate;
    private Customer customer;
    private Organization organization;
    private PaymentType paymentType;
    private List<InvoiceLines> invoiceLines;

    private BigDecimal totalNetto;

    private BigDecimal totalTax;

    private BigDecimal totalBrutto;


    public InvoiceDto() {
    }

    public InvoiceDto(String invoiceNumber, LocalDate issueDate, LocalDate serviceDate, Customer customer, Organization organization, PaymentType paymentType, List<InvoiceLines> invoiceLines) {
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.serviceDate = serviceDate;
        this.customer = customer;
        this.organization = organization;
        this.paymentType = paymentType;
        this.invoiceLines = invoiceLines;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<InvoiceLines> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLines> invoiceLines) {
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



