package tk.mbondos.dtos;

import tk.mbondos.domain.Product;

import java.math.BigDecimal;

public class InvoiceLinesDto {
    private int quantity = 1;
    private Product product;
    private BigDecimal priceTotal;

    public InvoiceLinesDto() {
    }

    public InvoiceLinesDto(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.priceTotal = product.getPriceBrutto().multiply(BigDecimal.valueOf(quantity));
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

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }
}
