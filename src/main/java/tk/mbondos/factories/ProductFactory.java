package tk.mbondos.factories;

import org.springframework.stereotype.Component;
import tk.mbondos.domain.Product;
import tk.mbondos.dtos.ProductDto;

@Component
public class ProductFactory {
    public ProductFactory() {
    }

    public Product create(ProductDto productDto) {
        return new Product(
                productDto.getName(),
                productDto.getPriceNetto(),
                productDto.getPriceBrutto(),
                productDto.getTaxRate());
    }
}
