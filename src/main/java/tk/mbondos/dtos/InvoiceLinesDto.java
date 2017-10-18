package tk.mbondos.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class InvoiceLinesDto {
    private Long id;
    @Min(1)
    private int quantity = 1;
    @NotNull
    private ProductDto product;
    @Min(0)
    private BigDecimal valueNetto;
    @Min(0)
    private BigDecimal valueBrutto;
    @Min(0)
    private BigDecimal valueTax;

    public InvoiceLinesDto() {
    }

    public InvoiceLinesDto(Long id, int quantity, ProductDto product, BigDecimal valueNetto, BigDecimal valueBrutto, BigDecimal valueTax) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.valueNetto = valueNetto;
        this.valueBrutto = valueBrutto;
        this.valueTax = valueTax;
    }

    public InvoiceLinesDto(int quantity, ProductDto product, BigDecimal valueNetto, BigDecimal valueBrutto, BigDecimal valueTax) {
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

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
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
