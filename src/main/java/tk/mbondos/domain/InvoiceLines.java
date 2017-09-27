package tk.mbondos.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
public class InvoiceLines {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value = 1)
    private int quantity ;

    @ManyToOne
    private Product product;

    private BigDecimal valueNetto;
    private BigDecimal valueBrutto;
    private BigDecimal valueTax;


    public InvoiceLines() {
    }

    public InvoiceLines(int quantity, Product product, BigDecimal valueNetto, BigDecimal valueBrutto, BigDecimal valueTax) {
        this.quantity = quantity;
        this.product = product;
        this.valueNetto = valueNetto;
        this.valueBrutto = valueBrutto;
        this.valueTax = valueTax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getValueNetto() {
        return valueNetto;
    }

    public void setValueNetto(BigDecimal valueNetto) {
        this.valueNetto = valueNetto;
    }

    public BigDecimal getValueBrutto() {
        return valueBrutto;
    }

    public void setValueBrutto(BigDecimal valueBrutto) {
        this.valueBrutto = valueBrutto;
    }

    public BigDecimal getValueTax() {
        return valueTax;
    }

    public void setValueTax(BigDecimal valueTax) {
        this.valueTax = valueTax;
    }
}
