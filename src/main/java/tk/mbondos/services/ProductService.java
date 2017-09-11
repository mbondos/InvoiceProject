package tk.mbondos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mbondos.domain.Product;
import tk.mbondos.dtos.ProductDto;
import tk.mbondos.factories.ProductFactory;
import tk.mbondos.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductFactory productFactory;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductFactory productFactory) {
        this.productRepository = productRepository;
        this.productFactory = productFactory;
    }

    @Transactional
    public void createProduct(ProductDto productDto) {
        Product product = productFactory.create(productDto);
        productRepository.save(product);
    }

    @Transactional
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }
}
