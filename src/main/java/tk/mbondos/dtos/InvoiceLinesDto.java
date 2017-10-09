package tk.mbondos.dtos;

import org.springframework.stereotype.Component;
import tk.mbondos.domain.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class InvoiceLinesDto {

    @Min(1)
    private int quantity = 1;
    @NotNull
    private Product product;
    @Min(0)
    private BigDecimal valueNetto;
    @Min(0)
    private BigDecimal valueBrutto;
    @Min(0)
    private BigDecimal valueTax;

    public InvoiceLinesDto() {
    }

    public InvoiceLinesDto(int quantity, Product product, BigDecimal valueNetto, BigDecimal valueBrutto, BigDecimal valueTax) {
        this.quantity = quantity;
        this.product = product;
        this.valueNetto = valueNetto;
        this.valueBrutto = valueBrutto;
        this.valueTax = valueTax;
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
