package tk.mbondos.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class InvoiceLines {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value = 1)
    private int quantity ;

    @ManyToOne
    private Product product;

    public InvoiceLines() {
    }

    public InvoiceLines(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
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
}
