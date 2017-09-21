package tk.mbondos.dtos;

import tk.mbondos.domain.Product;

public class InvoiceLinesDto {
    private int quantity = 1;
    private Product product;

    public InvoiceLinesDto() {
    }

    public InvoiceLinesDto(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
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
}
