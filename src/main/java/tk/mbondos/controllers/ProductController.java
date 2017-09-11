package tk.mbondos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tk.mbondos.domain.Product;
import tk.mbondos.dtos.ProductDto;
import tk.mbondos.services.ProductService;

import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getProducts() {
        return productService.listAllProducts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addProduct(ProductDto productDto) {
        productDto.validate();
        productService.createProduct(productDto);
    }

}
