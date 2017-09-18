package tk.mbondos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tk.mbondos.domain.Customer;
import tk.mbondos.domain.Invoice;
import tk.mbondos.dtos.CustomerDto;
import tk.mbondos.dtos.InvoiceDto;
import tk.mbondos.services.CustomerService;
import tk.mbondos.services.InvoiceService;

import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceHtmlController {
    InvoiceService invoiceService;
    CustomerService customerService;

    @Autowired
    public InvoiceHtmlController(InvoiceService invoiceService, CustomerService customerService) {
        this.invoiceService = invoiceService;
        this.customerService = customerService;
    }




/*
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getInvoices(Model model) {
        List<Invoice> invoices = invoiceService.listAllInvoices();
        model.addAttribute("invoices", invoices);
        model.addAttribute("title", "My Invoices");
        return "invoices/show";
    }
*/

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddInvoiceForm(Model model) {
        model.addAttribute("title", "Add Invoice");
        model.addAttribute("invoice", new InvoiceDto());
        model.addAttribute("customerId", new String());
        return "invoice";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddInvoiceForm(@RequestParam String customerId, @ModelAttribute InvoiceDto invoice) {
        //invoice.validate();
        //customerService.createCustomer(customerId);
        System.out.println(customerId);
       //invoiceService.createInvoice(invoice);
        return "redirect:add";
    }

}
