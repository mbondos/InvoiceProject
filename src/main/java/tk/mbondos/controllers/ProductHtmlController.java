package tk.mbondos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mbondos.domain.Product;
import tk.mbondos.dtos.ProductDto;
import tk.mbondos.services.ProductService;
import tk.mbondos.utils.PdfGeneratorUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("products")
public class ProductHtmlController {
    private ProductService productService;

    @Autowired
    private PdfGeneratorUtil pdfGeneratorUtil;

    @Autowired
    public ProductHtmlController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getProducts(Model model) {
        List<Product> products = productService.listAllProducts();
        model.addAttribute("products", products);
        return "products/show";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddProductsForm(Model model) {
        model.addAttribute("title", "Add Products");
        model.addAttribute("productDto", new ProductDto());
        return "products/add";
    }
    @RequestMapping(value = "pdf", method = RequestMethod.GET)
    public String generatePdf(Model model) {
        Map<String,String> data = new HashMap<String,String>();
        data.put("name", "Max");
        try {
            pdfGeneratorUtil.createPdf("pdftemplate", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddInvoiceForm(@ModelAttribute ProductDto productDto) {
        productDto.validate();
        productService.createProduct(productDto);
        return "redirect:";
    }
}
