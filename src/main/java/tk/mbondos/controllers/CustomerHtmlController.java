package tk.mbondos.controllers;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tk.mbondos.domain.Customer;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.dtos.InvoiceDto;
import tk.mbondos.services.CustomerService;
import tk.mbondos.services.InvoiceService;
import tk.mbondos.utils.InvoiceLinesWrapper;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerHtmlController {
    private CustomerService customerService;
    protected final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public CustomerHtmlController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("title", "My Customers");
        return "customer/show";
    }


    //GET add page
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "customer/add";
    }

    //POST add page
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCustomerForm(@Valid @ModelAttribute("customer") CustomerDto customer,
                                        BindingResult resultCustomer) {
        if (resultCustomer.hasErrors() ) {
            log.info("Customer add form has errors");

            return "customer/add";
        }

        customerService.createCustomer(customer);

        return "redirect:/customers";
    }
}
