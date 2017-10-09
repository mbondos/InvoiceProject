package tk.mbondos.dtos;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class ProductDto {
    @NotEmpty
    private String name;
    @Min(0)
    private BigDecimal priceNetto;
    @Min(0)
    private BigDecimal priceBrutto;
    @Min(0)
    private int taxRate = 23;

    public ProductDto() {
    }

    public ProductDto(String name, BigDecimal priceNetto, BigDecimal priceBrutto, int taxRate) {
        this.name = name;
        this.priceNetto = priceNetto;
        this.priceBrutto = priceBrutto;
        this.taxRate = taxRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPriceNetto() {
        return priceNetto;
    }

    public void setPriceNetto(BigDecimal priceNetto) {
        this.priceNetto = priceNetto;
    }

    public BigDecimal getPriceBrutto() {
        return priceBrutto;
    }

    public void setPriceBrutto(BigDecimal priceBrutto) {
        this.priceBrutto = priceBrutto;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public void validate() {

    }
}
