package tk.mbondos.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mbondos.enums.PaymentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotEmpty(message = "May not be empty!!!")
    private String invoiceNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate serviceDate;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Organization organization;

    private PaymentType paymentType;

    @OneToMany
    private List<InvoiceLines> invoiceLines;

    private BigDecimal totalNetto;

    private BigDecimal totalTax;

    private BigDecimal totalBrutto;



    public Invoice() {
    }

    public Invoice(String invoiceNumber, LocalDate issueDate, LocalDate serviceDate, Customer customer, Organization organization, PaymentType paymentType, List<InvoiceLines> invoiceLines, BigDecimal totalNetto, BigDecimal totalTax, BigDecimal totalBrutto) {
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
}
